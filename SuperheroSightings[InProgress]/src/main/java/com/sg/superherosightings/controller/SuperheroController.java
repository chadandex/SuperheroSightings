/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Heroes;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.service.SuperheroServiceLayer;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Chad
 */
@Controller
public class SuperheroController {

    @Autowired
    SuperheroServiceLayer service;

    @Inject
    public SuperheroController(SuperheroServiceLayer service) {
        this.service = service;
    }

    // ===================================
    // HOME PAGE
    // ===================================
    @RequestMapping(value = {"/", "/displayHomePage"}, method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        // Get all the Sightings from the DAO
        List<Sighting> sightingList = service.getAllSightingsToLimit();
        // Get all the Locations from the DAO
        List<Location> locationList = service.getAllLocation();
        // Get all the Heroes from the DAO
        List<Heroes> heroList = service.getAllHeroes();

        // Put the List of Sightings on the Model
        model.addAttribute("sightingList", sightingList);
        // Put the List of Locations on the Model
        model.addAttribute("locationList", locationList);
        // Put the List of Heroes on the Model
        model.addAttribute("heroList", heroList);

        // Return the logical name of our View component
        return "homePage";
    }

    // ===================================
    // HEROES
    // ===================================
    @RequestMapping(value = "/displayHeroesPage", method = RequestMethod.GET)
    public String displayHeroesPage(Model model) {
        // Get all the Heroes from the DAO
        List<Heroes> heroList = service.getAllHeroes();

        // Get all the Organizations from the DAO
        List<Organization> orgList = service.getAllOrg();

        // Put the List of Heroes on the Model
        model.addAttribute("heroList", heroList);

        // Put the List of Orgs on the Model
        model.addAttribute("orgList", orgList);

        // Return the logical name of our View component
        return "createHero";
    }

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request) {
        String orgIdParameter = request.getParameter("heroesOrgSelect");
        long orgId = Integer.parseInt(orgIdParameter);
        long heroId = 0;

        // grab the incoming values from the form and create a new Hero
        // object
        Heroes hero = new Heroes();
        hero.setHeroName(request.getParameter("heroName"));
        hero.setHeroDescr(request.getParameter("heroDescr"));
        hero.setHeroPower(request.getParameter("heroPower"));
        
        // persist the new Hero
        service.addHero(hero);
        // get heroId for HeroOrg bridge table
        heroId = hero.getHeroId();
 
        service.addHeroOrg(orgId, heroId);

        return "redirect:displayHeroesPage";
    }

    @RequestMapping(value = "/displayHeroDetails", method = RequestMethod.GET)
    public String displayHeroDetails(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);

        Heroes hero = service.getHeroById(heroId);
        List<Organization> org = service.getOrgByHeroId(heroId);

        model.addAttribute("hero", hero);
        model.addAttribute("org", org);

        return "viewHero";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request) {
        String heroIdParameter = request.getParameter("heroId");
        long heroId = Long.parseLong(heroIdParameter);
        service.removeHero(heroId);
        service.deleteHeroOrgsByHeroID(heroId);

        return "redirect:displayHeroesPage";
    }

    @RequestMapping(value = "/deleteHeroOrgAssoc", method = RequestMethod.GET)
    public String deleteHeroOrgAssoc(HttpServletRequest request) {
        String orgIdParameter = request.getParameter("orgId");
        long orgId = Long.parseLong(orgIdParameter);
        String heroIdParameter = request.getParameter("heroId");
        long heroId = Long.parseLong(heroIdParameter);

        service.removeHeroOrg(orgId, heroId);

        // allow page to return to the correct heroId we are editing 
        return "redirect:displayEditHeroForm?heroId=" + heroId;
    }

    @RequestMapping(value = "/displayEditHeroForm", method = RequestMethod.GET)
    public String displayEditHeroForm(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        long heroId = Long.parseLong(heroIdParameter);
        List<Organization> org = service.getOrgByHeroId(heroId);
        List<Organization> orgList = service.getAllOrg();

        Heroes hero = service.getHeroById(heroId);

        model.addAttribute("hero", hero);
        model.addAttribute("org", org);
        model.addAttribute("orgList", orgList);

        return "editHero";
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(@Valid @ModelAttribute("hero") Heroes hero, BindingResult result, HttpServletRequest request) {
        String orgIdParameter = request.getParameter("heroesOrgSelectEdit");

        if (result.hasErrors()) {
            return "editHero";
        }

        // if nothing is selected for an org, dont update anything related to org/hero relationship - else...
        if (orgIdParameter == null) {
            service.updateHero(hero);
        } else {
            long orgId = Integer.parseInt(orgIdParameter);
            service.updateHeroWithOrg(hero, orgId);
        }

        return "redirect:displayHeroesPage";
    }

    // ===================================
    // ORGANIZATION
    // ===================================
    @RequestMapping(value = "/displayOrganizationPage", method = RequestMethod.GET)
    public String displayOrganizationPage(Model model) {
        // Get all the Organizations from the DAO
        List<Organization> orgList = service.getAllOrg();

        // Put the List of Orgs on the Model
        model.addAttribute("orgList", orgList);

        // Return the logical name of our View component
        return "createOrg";
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Organization
        // object
        Organization org = new Organization();
        org.setOrgName(request.getParameter("orgName"));
        org.setOrgDescr(request.getParameter("orgDescr"));
        org.setOrgAddress(request.getParameter("orgAddress"));
        org.setOrgPhone(request.getParameter("orgPhone"));
        org.setOrgEmail(request.getParameter("orgEmail"));

        // persist the new Organization
        service.addOrg(org);

        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String orgIdParameter = request.getParameter("orgId");
        int orgId = Integer.parseInt(orgIdParameter);

        Organization org = service.getOrgById(orgId);
        List<Heroes> hero = service.getHeroByOrgId(orgId);

        model.addAttribute("org", org);
        model.addAttribute("hero", hero);

        return "viewOrg";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String orgIdParameter = request.getParameter("orgId");
        long orgId = Long.parseLong(orgIdParameter);
        service.removeOrg(orgId);
        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/deleteHeroFromOrg", method = RequestMethod.GET)
    public String deleteHeroFromOrg(HttpServletRequest request) {
        String orgIdParameter = request.getParameter("orgId");
        long orgId = Long.parseLong(orgIdParameter);
        String heroIdParameter = request.getParameter("heroId");
        long heroId = Long.parseLong(heroIdParameter);

        service.removeHeroOrg(orgId, heroId);

        // allow page to return to the correct orgId we are editing 
        return "redirect:displayEditOrgForm?orgId=" + orgId;
    }

    @RequestMapping(value = "/displayEditOrgForm", method = RequestMethod.GET)
    public String displayEditOrgForm(HttpServletRequest request, Model model) {
        String orgIdParameter = request.getParameter("orgId");
        long orgId = Long.parseLong(orgIdParameter);
        Organization org = service.getOrgById(orgId);
        List<Heroes> hero = service.getHeroByOrgId(orgId);
        List<Heroes> heroList = service.getAllHeroes();

        model.addAttribute("org", org);
        model.addAttribute("hero", hero);
        model.addAttribute("heroList", heroList);

        return "editOrg";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("org") Organization org, BindingResult result, HttpServletRequest request) {
        String heroIdParameter = request.getParameter("heroSelect");
        
        if (result.hasErrors()) {
            return "editOrg";
        }

        // if nothing is selected for a hero, dont update anything related to hero/org relationship - else...
        if (heroIdParameter == null) {
            service.updateOrg(org);
        } else {
            long heroId = Long.parseLong(heroIdParameter);
            service.updateOrgWithHero(org, heroId);
        }

        return "redirect:displayOrganizationPage";
    }

    // ===================================
    // LOCATION
    // ===================================
    @RequestMapping(value = "/displayLocationPage", method = RequestMethod.GET)
    public String displayLocationPage(Model model) {
        // Get all the Location from the DAO
        List<Location> locationList = service.getAllLocation();

        // Put the List of Locations on the Model
        model.addAttribute("locationList", locationList);

        // Return the logical name of our View component
        return "createLocation";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Location
        // object
        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setLocationDescr(request.getParameter("locationDescr"));
        location.setLocationAddress(request.getParameter("locationAddress"));
        location.setLocationLat(request.getParameter("locationLat"));
        location.setLocationLong(request.getParameter("locationLong"));

        // persist the new Location
        service.addLocation(location);

        return "redirect:displayLocationPage";
    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        Location location = service.getLocationById(locationId);

        model.addAttribute("location", location);

        return "viewLocation";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        long locationId = Long.parseLong(locationIdParameter);
        service.removeLocation(locationId);
        return "redirect:displayLocationPage";
    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        long locationId = Long.parseLong(locationIdParameter);
        Location location = service.getLocationById(locationId);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {

        if (result.hasErrors()) {
            return "editLocation";
        }

        service.updateLocation(location);

        return "redirect:displayLocationPage";
    }

    // ===================================
    // SIGHTINGS
    // ===================================
    @RequestMapping(value = "/displaySightingPage", method = RequestMethod.GET)
    public String displaySightingPage(Model model) {
        // Get all the Sightings from the DAO
        List<Sighting> sightingList = service.getAllSightings();
        // Get all the Locations from the DAO
        List<Location> locationList = service.getAllLocation();
        // Get all the Heroes from the DAO
        List<Heroes> heroList = service.getAllHeroes();

        // Put the List of Sighting on the Model
        model.addAttribute("sightingList", sightingList);
        // Put the List of Locations on the Model
        model.addAttribute("locationList", locationList);
        // Put the List of Heroes on the Model
        model.addAttribute("heroList", heroList);

        // Return the logical name of our View component
        return "createSighting";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationSelect");
        int locationId = Integer.parseInt(locationIdParameter);
        String heroIdParameter = request.getParameter("heroSelect");
        int heroId = Integer.parseInt(heroIdParameter);
        String sightingDateParameter = request.getParameter("sightingDate");
        LocalDate sightingDate = LocalDate.parse(sightingDateParameter);

        // grab the incoming values from the form and create a new Sighting
        // object
        Sighting sighting = new Sighting();
        sighting.setLocationId(locationId);
        sighting.setSightingDate(sightingDate);
        sighting.setHeroId(heroId);

        // persist the new Sighting
        service.addSighting(sighting);

        return "redirect:displaySightingPage";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = service.getSightingByID(sightingId);
        Location location = service.getLocationBySightingId(sightingId);
        Heroes hero = service.getHeroBySightingId(sightingId);

        String sightingDateParameter = request.getParameter("sightingDate");
        LocalDate sightingDate = sighting.getSightingDate();

        model.addAttribute("sighting", sighting);
        model.addAttribute("sightingDate", sightingDate);
        model.addAttribute("location", location);
        model.addAttribute("hero", hero);

        return "viewSighting";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        long sightingId = Long.parseLong(sightingIdParameter);
        service.removeSighting(sightingId);
        return "redirect:displaySightingPage";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        long sightingId = Long.parseLong(sightingIdParameter);

        List<Location> locationList = service.getAllLocation();
        List<Heroes> heroList = service.getAllHeroes();

        Sighting sighting = service.getSightingByID(sightingId);
        Location location = service.getLocationBySightingId(sightingId);
        Heroes hero = service.getHeroBySightingId(sightingId);

        model.addAttribute("sighting", sighting);
        model.addAttribute("location", location);
        model.addAttribute("hero", hero);

        model.addAttribute("locationList", locationList);
        model.addAttribute("heroList", heroList);

        return "editSighting";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result, HttpServletRequest request) {
        String heroIdParameter = request.getParameter("heroSelect");
        String locationIdParameter = request.getParameter("locationSelect");
        
        if (result.hasErrors()) {
            return "editSighting";
        }

        // if nothing is selected for a hero, dont update anything related to hero/org relationship - else...
       /* if (heroIdParameter == null && locationIdParameter == null) {
            service.updateSighting(sighting);
        } else if (heroIdParameter == null) {
            long heroId = Long.parseLong(heroIdParameter);
            service.updateOrgWithHero(org, heroId);
        }*/
        service.updateSighting(sighting);

        return "redirect:displaySightingPage";
    }
}
