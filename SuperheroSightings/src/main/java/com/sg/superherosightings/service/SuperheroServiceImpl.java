/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SuperheroDao;
import com.sg.superherosightings.dto.Heroes;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chad
 */
@Service
public class SuperheroServiceImpl implements SuperheroServiceLayer {

    @Autowired(required = false)
    SuperheroDao dao;

    public SuperheroServiceImpl(SuperheroDao dao) {
        this.dao = dao;
    }

    @Override
    public void addHero(Heroes hero) {
        dao.addHero(hero);
    }

    @Override
    public void removeHero(long heroId) {
        dao.removeHero(heroId);
    }

    @Override
    public void updateHeroWithOrg(Heroes hero, long orgId) {
        dao.updateHeroWithOrg(hero, orgId);
    }

    @Override
    public void updateHero(Heroes hero) {
        dao.updateHero(hero);
    }

    @Override
    public List<Heroes> getHeroesByLocationId(int locationId) {
        return dao.getHeroesByLocationId(locationId);
    }

    @Override
    public List<Heroes> getHeroesByOrgId(int orgId) {
        return dao.getHeroesByOrgId(orgId);
    }

    @Override
    public List<Heroes> getAllHeroes() {
        return dao.getAllHeroes();
    }

    @Override
    public Heroes getHeroById(long heroId) {
        return dao.getHeroById(heroId);
    }

    @Override
    public List<Organization> getOrgByHeroId(long heroId) {
        return dao.getOrgByHeroId(heroId);
    }

    @Override
    public void addOrg(Organization organization) {
        dao.addOrg(organization);
    }

    @Override
    public void removeOrg(long orgId) {
        dao.removeOrg(orgId);
    }

    @Override
    public void updateOrgWithHero(Organization org, long heroId) {
        dao.updateOrgWithHero(org, heroId);
    }

    @Override
    public void updateOrg(Organization organization) {
        dao.updateOrg(organization);
    }

    @Override
    public List<Organization> getAllOrg() {
        return dao.getAllOrg();
    }

    @Override
    public Organization getOrgById(long orgId) {
        return dao.getOrgById(orgId);
    }

    @Override
    public List<Heroes> getHeroByOrgId(long orgId) {
        return dao.getHeroByOrgId(orgId);
    }

    @Override
    public Location addLocation(Location location) {
        return dao.addLocation(location);
    }

    @Override
    public void removeLocation(long locationId) {
        dao.removeLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        dao.updateLocation(location);
    }

    @Override
    public List<Location> getAllLocation() {
        return dao.getAllLocation();
    }

    @Override
    public Location getLocationById(long locationId) {
        return dao.getLocationById(locationId);
    }

    @Override
    public void addSighting(Sighting sighting) {
        dao.addSighting(sighting);
    }

    @Override
    public Sighting getSightingByID(long sightingId) {
        return dao.getSightingByID(sightingId);
    }

    @Override
    public Location getLocationBySightingId(long sightingID) {
        return dao.getLocationBySightingId(sightingID);
    }

    @Override
    public Heroes getHeroBySightingId(long sightingID) {
        return dao.getHeroBySightingId(sightingID);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @Override
    public List<Sighting> getAllSightingsToLimit() {
        return dao.getAllSightingsToLimit();
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return dao.getSightingsByDate(date);
    }

    @Override
    public void updateSighting(Sighting sighting, long heroId, long locationId) {
        dao.updateSighting(sighting, heroId, locationId);
    }

    @Override
    public List<Sighting> getSightingsByLocationID(int locationId) {
        return dao.getSightingsByLocationID(locationId);
    }

    @Override
    public void removeSighting(long sightingId) {
        dao.removeSighting(sightingId);
    }

    @Override
    public void addHeroOrg(long orgId, long heroId) {
        dao.addHeroOrg(orgId, heroId);
    }

    @Override
    public void removeHeroOrg(long heroId, long orgId) {
        dao.removeHeroOrg(heroId, orgId);
    }

    @Override
    public void deleteHeroOrgsByHeroID(long heroID) {
        dao.deleteHeroOrgsByHeroID(heroID);
    }
}
