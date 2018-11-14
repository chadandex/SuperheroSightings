/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dto.Heroes;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Chad
 */
public interface SuperheroServiceLayer {
    // HEROES
    // add the given Hero to the data store
    public void addHero(Heroes hero);

    // remove the Hero with the given id from the data store
    public void removeHero(long heroId);

    // update the given Hero in the data store with an org attached
    public void updateHeroWithOrg(Heroes hero, long orgId);
    
    // update the given Hero in the data store without an org attached
    public void updateHero(Heroes hero);
    
    // retrieve all Hero from location
    public List<Heroes> getHeroesByLocationId(int locationId);
    
    // retrieve all Hero from org 
    public List<Heroes> getHeroesByOrgId(int orgId);
    
    // retrieve all Hero from the data store
     public List<Heroes> getAllHeroes();
     
     // retrieve the Hero with the given id from the data store
    public Heroes getHeroById(long heroId);
    
     // retrieve the Org with the given hero id from the data store
    public List<Organization> getOrgByHeroId(long heroId);
    
    // ORGANIZATION
    // add the given Organization to the data store
    public void addOrg(Organization organization);

    // remove the Organization with the given id from the data store
    public void removeOrg(long orgId);

    // update the given Organization in the data store with a hero attached
    public void updateOrgWithHero(Organization org, long heroId);
    
    // update the given Organization in the data store
    public void updateOrg(Organization organization);

    // retrieve all Organization from the data store
    public List<Organization> getAllOrg();

    // retrieve the Organization with the given id from the data store
    public Organization getOrgById(long orgId);
    
    // retrieve the Hero(es) with the given org id from the data store
    public List<Heroes> getHeroByOrgId(long orgId);
    
    // LOCATION
    // add the given Location to the data store
    public Location addLocation(Location location);

    // remove the Location with the given id from the data store
    public void removeLocation(long locationId);

    // update the given Location in the data store
    public void updateLocation(Location location);

    // retrieve all Location from the data store
    public List<Location> getAllLocation();

    // retrieve the Location with the given id from the data store
    public Location getLocationById(long locationId);
    
    // SIGHTINGS
    // add given Sighting to the data store
    public void addSighting(Sighting sighting);
    
    // retrieve the sighting with the given id from the data store
    public Sighting getSightingByID(long sightingID);
    
    // retrieve the location with given sighting id from the data store
    public Location getLocationBySightingId(long sightingID);
    
    // retrieve the hero with given sighting id from the data store
    public Heroes getHeroBySightingId(long sightingID);
    
    // retrieve all Sightings from data store
    public List<Sighting> getAllSightings();
    
    // retrieve all Sightings, no more than 10, from data store
    public List<Sighting> getAllSightingsToLimit();
    
    // retrieve all Sightings with given date from the data store
    public List<Sighting> getSightingsByDate(LocalDate date);
    
    // update the given Sighthing in the data store
    public void updateSighting(Sighting sighting);
    
    // update the given Sighting with new hero selected
    public void updateSightingWithHero(Sighting sighting, long heroId);
    
    // update the given Sighting with new location selected
    public void updateSightingWithLocation(Sighting sighting, long locationId);
    
    // retrieve all Sighthings with given Location id
    public List<Sighting> getSightingsByLocationID(int locationID);
    
    // remove the Sighthing with the given id from the data store
    public void removeSighting(long sightingID);
    
    // HEROORG
    // add given hero to org
    public void addHeroOrg(long orgId, long heroId);
    
    // remove given hero from org
    public void removeHeroOrg(long heroId, long orgId);
    
    // remove given heroId from heroOrg bridge
    public void deleteHeroOrgsByHeroID(long heroId);
}
