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
public class SuperheroDaoTest {
    
    SuperheroDao dao;
    
    public SuperheroDaoTest() {
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

        dao = ctx.getBean("superheroDao", SuperheroDao.class);

        // delete all heroes
        List<Heroes> hero = dao.getAllHeroes();
        for (Heroes currentHero : hero) {
            dao.removeHero(currentHero.getHeroId());
        }
        // delete all organizations
        List<Organization> org = dao.getAllOrg();
        for (Organization currentOrg : org) {
            dao.removeOrg(currentOrg.getOrgId());
        }
        // delete all locations
        List<Location> location = dao.getAllLocation();
        for (Location currentLocation : location) {
            dao.removeLocation(currentLocation.getLocationId());
        }
        // delete all sightings
        List<Sighting> sighting = dao.getAllSightings();
        for (Sighting currentSighting : sighting) {
            dao.removeSighting(currentSighting.getSightingId());
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

        dao.addHero(hero);

        Heroes fromDao = dao.getHeroById(hero.getHeroId());
        assertEquals(fromDao, hero);
    }

    @Test
    public void deleteHero() {
        Heroes hero = new Heroes();
        hero.setHeroName("Superman");
        hero.setHeroDescr("Alien that is human");
        hero.setHeroPower("All");

        dao.addHero(hero);

        Heroes fromDao = dao.getHeroById(hero.getHeroId());
        assertEquals(fromDao, hero);
        dao.removeHero(hero.getHeroId());
        assertNull(dao.getHeroById(hero.getHeroId()));
    }
    
    @Test
    public void addGetLocation() {
        Location location = new Location();
        location.setLocationName("Outskirts");
        location.setLocationDescr("Don't walk through alone basically");
        location.setLocationAddress("001 UhOh Lane, OH");
        location.setLocationLat("123456789");
        location.setLocationLong("987654321");
        
        dao.addLocation(location);
        
        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
    }

    @Test
    public void deleteLocation() {
        Location location = new Location();
        location.setLocationName("Outskirts");
        location.setLocationDescr("Don't walk through alone basically");
        location.setLocationAddress("001 UhOh Lane, OH");
        location.setLocationLat("123456789");
        location.setLocationLong("987654321");
        
        dao.addLocation(location);

        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
        dao.removeLocation(location.getLocationId());
        assertNull(dao.getLocationById(location.getLocationId()));
    }
    
    @Test
    public void addGetOrg() {
        Organization org = new Organization();
        org.setOrgName("Okay Guys");
        org.setOrgDescr("They pretty okay guys and gals");
        org.setOrgAddress("111 Decent Lane, OH");
        org.setOrgPhone("5555555555");
        org.setOrgEmail("okayguys@oguys.com");
        
        dao.addOrg(org);
        
        Organization fromDao = dao.getOrgById(org.getOrgId());
        assertEquals(fromDao, org);
    }

    @Test
    public void deleteOrg() {
        Organization org2 = new Organization();
        org2.setOrgName("Okay Guys2");
        org2.setOrgDescr("They pretty okay guys and gals");
        org2.setOrgAddress("111 Decent Lane, OH");
        org2.setOrgPhone("5555555555");
        org2.setOrgEmail("okayguys@oguys.com");
        
        dao.addOrg(org2);

        Organization fromDao = dao.getOrgById(org2.getOrgId());
        assertEquals(fromDao, org2);
        dao.removeOrg(org2.getOrgId());
        assertNull(dao.getOrgById(org2.getOrgId()));
    }*/
}
