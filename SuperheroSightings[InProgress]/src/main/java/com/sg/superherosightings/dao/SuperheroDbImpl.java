/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Heroes;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chad
 */
public class SuperheroDbImpl implements SuperheroDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //----------- HEROES -----------
    private static final String SQL_INSERT_HERO
            = "insert into heroes "
            + "(heroName, heroDescr, heroPower) "
            + "values (?, ?, ?)";
    private static final String SQL_DELETE_HERO
            = "delete from heroes where heroId = ?";
    private static final String SQL_DELETE_HEROES_ORG
            = "delete from heroOrg where heroId = ?";
    private static final String SQL_SELECT_HERO
            = "select * from heroes where heroId = ?";
    private static final String SQL_UPDATE_HERO
            = "update heroes set "
            + "heroName = ?, heroDescr = ?, heroPower = ?"
            + "where heroId = ?";
    private static final String SQL_SELECT_ALL_HERO
            = "select * from heroes";
    private static final String SQL_SELECT_ORGANIZATION_BY_HEROID
            = "select org.orgId, org.orgName, org.orgDescr, org.orgAddress, "
            + "org.orgPhone, org.orgEmail from organization org "
            + "inner join heroOrg on heroOrg.orgId = org.orgId "
            + "inner join heroes on heroes.heroId = heroOrg.heroId "
            + "where heroes.heroId = ? order by org.orgName";
    private static final String SQL_SELECT_HEROES_BY_LOCATIONID
            = "select h.heroId, h.heroName, h.heroDescr, h.heroPower "
            + "from heroes h join heroLocation hL on locationId "
            + "where h.heroId = hL.heroId "
            + "and hl.locationId = ?;";

    //----------- ORGANIZATION -----------
    private static final String SQL_INSERT_ORG
            = "insert into organization "
            + "(orgName, orgDescr, orgAddress, orgPhone, orgEmail) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ORG
            = "delete from organization where orgId = ?";
    private static final String SQL_DELETE_ORG_HEROES
            = "delete from heroOrg where orgId = ?";
    private static final String SQL_SELECT_ORG
            = "select * from organization where orgId = ?";
    private static final String SQL_UPDATE_ORG
            = "update organization set "
            + "orgName = ?, orgDescr = ?, orgAddress = ?, "
            + "orgPhone = ?, orgEmail = ? "
            + "where orgId = ?";
    private static final String SQL_SELECT_ALL_ORG
            = "select * from organization";
    private static final String SQL_SELECT_MEMBERS_BY_ORGID
            = "select me.heroId, me.heroName, me.heroDescr, me.heroPower from heroes me "
            + "join heroOrg om on me.heroId = om.heroId where "
            + "om.orgId = ?";
    private static final String SQL_SELECT_HEROES_BY_ORGID
            = "select * from heroOrg where orgId = ?";
    private static final String SQL_SELECT_HERO_BY_ORGID
            = "select hero.heroId, hero.heroName, hero.heroDescr, hero.heroPower from heroes hero "
            + "inner join heroOrg on heroOrg.heroId = hero.heroId "
            + "inner join organization org on org.orgId = heroOrg.orgId "
            + "where org.orgId = ? ";

    //----------- LOCATIONS -----------
    private static final String SQL_INSERT_LOCATION
            = "insert into location "
            + "(locationName, locationDescr, locationAddress, locationLat, locationLong) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_LOCATION
            = "delete from location where locationId = ?";
    private static final String SQL_SELECT_LOCATION
            = "select * from location where locationId = ?";
    private static final String SQL_UPDATE_LOCATION
            = "update location set "
            + "locationName = ?, locationDescr = ?, locationAddress = ?, locationLat = ?, "
            + "locationLong  = ? "
            + "where locationId = ?";
    private static final String SQL_SELECT_ALL_LOCATION
            = "select * from location";
    private static final String SQL_SELECT_LOCATIONS_BY_HEROID
            = "select lo.locationId, lo.locationName, lo.locationDescr, lo.locationAddress, lo.locationAddress, "
            + "lo.locationLat, lo.locationLong from location lo "
            + "join heroLocation hl on lo.locationId = hl.locationId where "
            + "hl.heroId = ?";

    //----------- SIGHTINGS -----------
    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (locationId, sightingDate, heroId) values (?,?,?)";
    private static final String SQL_UPDATE_SIGHTING_BY_ID
            = "update sighting set " 
            + "locationId = ?, sightingDate = ?, heroId = ? " 
            + "where sightingId = ?";
    private static final String SQL_DELETE_SIGHTING_BY_ID
            = "delete from sighting where sightingId = ?";
    private static final String SQL_SELECT_SIGHTING_BY_ID
            = "select sighting.sightingId, location.*, sighting.sightingDate, heroes.* from sighting"
            + " inner join location on location.locationId = sighting.locationId"
            + " inner join heroes on heroes.heroId = sighting.heroId"
            + " where sighting.sightingId = ?";
    private static final String SQL_SELECT_LOCATION_BY_SIGHTINGID
            = "select lo.locationId, lo.locationName, lo.locationDescr, lo.locationAddress, lo.locationAddress, "
            + "lo.locationLat, lo.locationLong from location lo "
            + "inner join sighting on sighting.locationId = lo.locationId "
            + "where sighting.sightingId = ? ";
    private static final String SQL_SELECT_HERO_BY_SIGHTINGID
            = "select hero.heroId, hero.heroName, hero.heroDescr, hero.heroPower from heroes hero "
            + "inner join sighting on sighting.heroId = hero.heroId "
            + "where sighting.sightingId = ? ";
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select heroes.*, location.*, sighting.sightingId, sighting.sightingDate from location"
            + " inner join sighting on location.locationId = sighting.locationId"
            + " inner join heroes on heroes.heroId = sighting.heroId order by sighting.sightingDate desc";
    private static final String SQL_SELECT_SIGHTINGS_ORDER_BY_DATE_LIMIT_10
            = "select heroes.*, location.*, sighting.sightingId, sighting.sightingDate from location"
            + " inner join sighting on location.locationId = sighting.locationId"
            + " inner join heroes on heroes.heroId = sighting.heroId order by sighting.sightingDate desc limit 0,10";
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "select heroes.*, location.*, sighting.sightingId, sighting.sightingDate from location"
            + " inner join sighting on location.locationId = sighting.locationId"
            + " inner join heroes on heroes.heroId = sighting.heroId"
            + " where sighting.sightingDate = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
            = "select heroes.*, location.*, sighting.sightingId, sighting.sightingDate from location"
            + " inner join sighting on location.locationId = sighting.locationId"
            + " inner join heroes on heroes.heroId = sighting.heroId"
            + " where location.locationId = ?";
    private static final String SQL_DELETE_SIGHTINGS_BY_HERO_ID
            = "delete from sighting where heroId = ?";
    private static final String SQL_DELETE_SIGHTINGS_BY_LOCATION_ID
            = "delete from sighting where locationId = ?";

    //----------- HEROLOCATION -----------
    private static final String SQL_INSERT_HEROES_LOCATION
            = "insert into heroLocation (heroId, locationId) values(?, ?)";

    //----------- HEROORG -----------
    private static final String SQL_INSERT_HERO_ORG
            = "insert into heroOrg (orgId, heroId) values(?, ?)";
    private static final String SQL_DELETE_HERO_ORG
            = "delete from heroOrg where orgId = ? and heroId = ?";
    private static final String SQL_DELETE_HERO_ORG_BY_HERO_ID
            = "delete from heroOrg where heroId = ?";

    // ===================================
    // HEROES
    // ===================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Heroes hero) {
        jdbcTemplate.update(SQL_INSERT_HERO,
                hero.getHeroName(),
                hero.getHeroDescr(),
                hero.getHeroPower());

        hero.setHeroId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        
        // now insert heroes org(s)
        /*List<Organization> orgId = hero.getOrg();
        for (Organization orgIds : orgId) {
            jdbcTemplate.update(SQL_INSERT_HERO_ORG, 
                                orgId, 
                                hero.getHeroId());
        }*/

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeHero(long heroId) {
        // delete heroOrg by heroes id for this hero
        jdbcTemplate.update(SQL_DELETE_HERO_ORG_BY_HERO_ID, heroId);
        // delete Sighting relationship for this hero
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS_BY_HERO_ID, heroId);
        // delete hero
        jdbcTemplate.update(SQL_DELETE_HERO, heroId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateHeroWithOrg(Heroes hero, long orgId) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getHeroName(),
                hero.getHeroDescr(),
                hero.getHeroPower(),
                hero.getHeroId());
        // delete heroOrg relationship for this hero, reset after
        jdbcTemplate.update(SQL_DELETE_HEROES_ORG, hero.getHeroId());
        
        addHeroOrg(orgId, hero.getHeroId());
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateHero(Heroes hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getHeroName(),
                hero.getHeroDescr(),
                hero.getHeroPower(),
                hero.getHeroId());
    }

    @Override
    public List<Heroes> getHeroesByLocationId(int locationId) {
        // get the heroes from this location sighting
        List<Heroes> heroList
                = jdbcTemplate.query(SQL_SELECT_HEROES_BY_LOCATIONID,
                        new HeroMapper(),
                        locationId);
        // set the org and list of locations for each hero
        return associateOrganizationAndLocationsWithHeros(heroList);
    }

    @Override
    public List<Heroes> getHeroesByOrgId(int orgId) {
        // get the heroes apart of org
        List<Heroes> heroList
                = jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORGID,
                        new HeroMapper(),
                        orgId);
        // set the org and list of locations for each hero
        return associateOrganizationAndLocationsWithHeros(heroList);
    }

    @Override
    public List<Heroes> getAllHeroes() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HERO, new HeroMapper());

    }

    @Override
    public Heroes getHeroById(long heroId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO, new HeroMapper(), heroId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Organization> getOrgByHeroId(long heroId) {
        try {
            return jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_HEROID, new OrgMapper(), heroId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    // ===================================
    // ORGANIZATION
    // ===================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrg(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORG,
                organization.getOrgName(),
                organization.getOrgDescr(),
                organization.getOrgAddress(),
                organization.getOrgPhone(),
                organization.getOrgEmail());

        // query the database for the id that was just assigned to the new
        // row in the database
        organization.setOrgId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeOrg(long orgId) {
        // delete heroOrg relationship for this org
        jdbcTemplate.update(SQL_DELETE_ORG_HEROES, orgId);
        // delete org
        jdbcTemplate.update(SQL_DELETE_ORG, orgId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrgWithHero(Organization org, long heroId) {
        jdbcTemplate.update(SQL_UPDATE_ORG,
                org.getOrgName(),
                org.getOrgDescr(),
                org.getOrgAddress(),
                org.getOrgPhone(),
                org.getOrgEmail(),
                org.getOrgId());
        // delete heroOrg relationship for this org and then reset them
        jdbcTemplate.update(SQL_DELETE_ORG_HEROES, org.getOrgId());

        addHeroOrg(org.getOrgId(), heroId);
    }
    
    @Override
    public void updateOrg(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORG,
                org.getOrgName(),
                org.getOrgDescr(),
                org.getOrgAddress(),
                org.getOrgPhone(),
                org.getOrgEmail(),
                org.getOrgId());
    }

    @Override
    public List<Organization> getAllOrg() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORG, new SuperheroDbImpl.OrgMapper());
    }

    @Override
    public Organization getOrgById(long orgId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORG, new SuperheroDbImpl.OrgMapper(), orgId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Heroes> getHeroByOrgId(long orgId) {
        try {
            return jdbcTemplate.query(SQL_SELECT_HERO_BY_ORGID, new HeroMapper(), orgId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    // ===================================
    // LOCATION
    // ===================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescr(),
                location.getLocationAddress(),
                location.getLocationLat(),
                location.getLocationLong());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        // set the new id value on the contact object and return it
        location.setLocationId(newId);
        return location;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeLocation(long locationId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS_BY_LOCATION_ID, locationId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescr(),
                location.getLocationAddress(),
                location.getLocationLat(),
                location.getLocationLong(),
                location.getLocationId());
    }

    @Override
    public List<Location> getAllLocation() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATION, new SuperheroDbImpl.LocationMapper());
    }

    @Override
    public Location getLocationById(long locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new SuperheroDbImpl.LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    // ===================================
    // SIGHTINGS
    // ===================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocationId(),
                sighting.getSightingDate().toString(),
                sighting.getHeroId());

        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

    }

    @Override
    public Sighting getSightingByID(long sightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public Location getLocationBySightingId(long sightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTINGID, new LocationMapper(), sightingID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public Heroes getHeroBySightingId(long sightingID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO_BY_SIGHTINGID, new HeroMapper(), sightingID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    public List<Sighting> getAllSightingsToLimit() {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_ORDER_BY_DATE_LIMIT_10, new SightingMapper());
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), date.toString());
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING_BY_ID,
                sighting.getLocationId(),
                sighting.getSightingDate().toString(),
                sighting.getHeroId(),
                sighting.getSightingId());
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSightingWithHero(Sighting sighting, long heroId) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING_BY_ID,
                sighting.getLocationId(),
                sighting.getSightingDate().toString(),
                sighting.getHeroId(),
                sighting.getSightingId());
        // delete heroOrg relationship for this org and then reset them
        //jdbcTemplate.update(SQL_DELETE_ORG_HEROES, org.getOrgId());

        addSightingHero(sighting.getSightingId(), heroId);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSightingWithLocation(Sighting sighting, long locationId) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING_BY_ID,
                sighting.getLocationId(),
                sighting.getSightingDate().toString(),
                sighting.getHeroId(),
                sighting.getSightingId());
        // delete heroOrg relationship for this org and then reset them
        //jdbcTemplate.update(SQL_DELETE_ORG_HEROES, org.getOrgId());

        addSightingLocation(sighting.getSightingId(), locationId);
    }

    @Override
    public List<Sighting> getSightingsByLocationID(int locationID) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, new SightingMapper(), locationID);
    }

    @Override
    public void removeSighting(long sightingID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_ID, sightingID);
    }
    
    // ===================================
    // HEROORG
    // ===================================
    @Override
    public void addHeroOrg(long orgID, long heroID) {
        jdbcTemplate.update(SQL_INSERT_HERO_ORG, orgID, heroID);
    }

    @Override
    public void removeHeroOrg(long orgID, long heroID) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG, orgID, heroID);
    }

    @Override
    public void deleteHeroOrgsByHeroID(long heroID) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG_BY_HERO_ID, heroID);
    }

    // ===================================
    // HELPER METHODS
    // ===================================
    private void addSightingHero(long sightingId, long heroId) {
        
    }
    
    private void addSightingLocation(long sightingId, long locationId){
        
    }
    
    private void insertOrgHeroes(Organization org) {
        final long orgId = org.getOrgId();
        final List<Heroes> hero = org.getHeroes();

        // Update the heroOrg bridge table with an entry for 
        // each member of this organization
        for (Heroes currentHero : hero) {
            jdbcTemplate.update(SQL_INSERT_HERO_ORG,
                    orgId,
                    currentHero.getHeroId());
        }
    }

    private void insertHeroLocation(Heroes hero) {
        final long heroId = hero.getHeroId();
        final List<Location> locations = hero.getLocations();

        // Update the heroLocation bridge table with an entry for 
        // each location of this hero
        for (Location currentLocation : locations) {
            jdbcTemplate.update(SQL_INSERT_HEROES_LOCATION,
                    heroId,
                    currentLocation.getLocationId());
        }
    }

    private List<Heroes> findHeroesForOrganization(Organization org) {
        return jdbcTemplate.query(SQL_SELECT_MEMBERS_BY_ORGID,
                new SuperheroDbImpl.HeroMapper(),
                org.getOrgId());
    }

    private List<Location> findLocationsForHero(Heroes hero) {
        return jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_HEROID,
                new SuperheroDbImpl.LocationMapper(),
                hero.getHeroId());
    }

    private List<Organization> findOrganizationForHero(Heroes hero) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_HEROID,
                new SuperheroDbImpl.OrgMapper(),
                hero.getHeroId());
    }

    private List<Organization>
            associateHeroWithOrg(List<Organization> orgList) {
        // set the complete list of hero ids for each organization
        for (Organization currentOrg : orgList) {
            // add Members to current org
            currentOrg.setHeroes(findHeroesForOrganization(currentOrg));
        }
        return orgList;
    }

    private List<Heroes>
            associateOrganizationAndLocationsWithHeros(List<Heroes> heroList) {
        // set the complete list of location ids for each hero
        for (Heroes currentHero : heroList) {
            // add Locations to current hero
            currentHero.setLocation(findLocationsForHero(currentHero));
            // add the Organization to current hero
            currentHero.setOrg(findOrganizationForHero(currentHero));
        }
        return heroList;
    }

    // ===================================
    // MAPPERS
    // ===================================
    private static final class HeroMapper implements RowMapper<Heroes> {

        public Heroes mapRow(ResultSet rs, int rowNum) throws SQLException {
            Heroes hero = new Heroes();
            hero.setHeroId(rs.getLong("heroId"));
            hero.setHeroName(rs.getString("heroName"));
            hero.setHeroDescr(rs.getString("heroDescr"));
            hero.setHeroPower(rs.getString("heroPower"));
            return hero;
        }
    }

    private static final class OrgMapper implements RowMapper<Organization> {

        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization org = new Organization();
            org.setOrgId(rs.getLong("orgId"));
            org.setOrgName(rs.getString("orgName"));
            org.setOrgDescr(rs.getString("orgDescr"));
            org.setOrgAddress(rs.getString("orgAddress"));
            org.setOrgPhone(rs.getString("orgPhone"));
            org.setOrgEmail(rs.getString("orgEmail"));
            return org;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getLong("locationId"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescr(rs.getString("locationDescr"));
            location.setLocationAddress(rs.getString("locationAddress"));
            location.setLocationLat(rs.getString("locationLat"));
            location.setLocationLong(rs.getString("locationLong"));
            return location;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Heroes hero = new Heroes();
            hero.setHeroId(rs.getLong("heroId"));
            hero.setHeroName(rs.getString("heroName"));
            hero.setHeroDescr(rs.getString("heroDescr"));
            hero.setHeroPower(rs.getString("heroPower"));
            Location location = new Location();
            location.setLocationId(rs.getLong("locationId"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescr(rs.getString("locationDescr"));
            location.setLocationAddress(rs.getString("locationAddress"));
            location.setLocationLat(rs.getString("locationLat"));
            location.setLocationLong(rs.getString("locationLong"));
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setHeroId(rs.getInt("heroId"));
            sighting.setLocationId(rs.getInt("locationId"));
            sighting.setSightingDate(LocalDate.parse(rs.getDate("sightingDate").toString()));
            return sighting;
        }
    }
}
