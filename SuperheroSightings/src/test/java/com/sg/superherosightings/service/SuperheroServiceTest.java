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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Chad
 */
public class SuperheroServiceTest {
    
    SuperheroServiceLayer service;
    
    public SuperheroServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
      = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        service = ctx.getBean("superheroService", SuperheroServiceLayer.class);

        // delete all heroes
        List<Heroes> hero = service.getAllHeroes();
        for (Heroes currentHero : hero) {
            service.removeHero(currentHero.getHeroId());
        }
        // delete all organizations
        List<Organization> org = service.getAllOrg();
        for (Organization currentOrg : org) {
            service.removeOrg(currentOrg.getOrgId());
        }
        // delete all locations
        List<Location> location = service.getAllLocation();
        for (Location currentLocation : location) {
            service.removeLocation(currentLocation.getLocationId());
        }
        // delete all sightings
        List<Sighting> sighting = service.getAllSightings();
        for (Sighting currentSighting : sighting) {
            service.removeSighting(currentSighting.getSightingId());
        }
    }
    
    @After
    public void tearDown() {
    }
    /*
    @Test
    public void addGetHero() {
        Heroes hero = new Heroes();
        hero.setHeroName("Batman");
        hero.setHeroDescr("Lost his parents");
        hero.setHeroPower("Money");

        service.addHero(hero);

        Heroes fromService = service.getHeroById(hero.getHeroId());
        assertEquals(fromService, hero);
    }

    @Test
    public void deleteHero() {
        Heroes hero = new Heroes();
        hero.setHeroName("Superman");
        hero.setHeroDescr("Alien that is human");
        hero.setHeroPower("All");

        service.addHero(hero);

        Heroes fromService = service.getHeroById(hero.getHeroId());
        assertEquals(fromService, hero);
        service.removeHero(hero.getHeroId());
        assertNull(service.getHeroById(hero.getHeroId()));
    }
    
    @Test
    public void addGetLocation() {
        Location location = new Location();
        location.setLocationName("Outskirts");
        location.setLocationDescr("Don't walk through alone basically");
        location.setLocationAddress("001 UhOh Lane, OH");
        location.setLocationLat("123456789");
        location.setLocationLong("987654321");
        
        service.addLocation(location);
        
        Location fromService = service.getLocationById(location.getLocationId());
        assertEquals(fromService, location);
    }

    @Test
    public void deleteLocation() {
        Location location = new Location();
        location.setLocationName("Outskirts");
        location.setLocationDescr("Don't walk through alone basically");
        location.setLocationAddress("001 UhOh Lane, OH");
        location.setLocationLat("123456789");
        location.setLocationLong("987654321");
        
        service.addLocation(location);

        Location fromService = service.getLocationById(location.getLocationId());
        assertEquals(fromService, location);
        service.removeLocation(location.getLocationId());
        assertNull(service.getLocationById(location.getLocationId()));
    }
    
    @Test
    public void addGetOrg() {
        Organization org = new Organization();
        org.setOrgName("Okay Guys");
        org.setOrgDescr("They pretty okay guys and gals");
        org.setOrgAddress("111 Decent Lane, OH");
        org.setOrgPhone("5555555555");
        org.setOrgEmail("okayguys@oguys.com");
        
        service.addOrg(org);
        
        Organization fromService = service.getOrgById(org.getOrgId());
        assertEquals(fromService, org);
    }

    @Test
    public void deleteOrg() {
        Organization org2 = new Organization();
        org2.setOrgName("Okay Guys2");
        org2.setOrgDescr("They pretty okay guys and gals");
        org2.setOrgAddress("111 Decent Lane, OH");
        org2.setOrgPhone("5555555555");
        org2.setOrgEmail("okayguys@oguys.com");
        
        service.addOrg(org2);

        Organization fromService = service.getOrgById(org2.getOrgId());
        assertEquals(fromService, org2);
        service.removeOrg(org2.getOrgId());
        assertNull(service.getOrgById(org2.getOrgId()));
    }*/
}
