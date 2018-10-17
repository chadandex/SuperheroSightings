/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dto;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Chad
 */
public class Heroes {

    private long heroId;
    @NotEmpty(message = "You must supply a value for the Hero Name.")
    @Length(max = 50, message = "Hero Name must be no more than 50 characters in length.")
    private String heroName;
    @NotEmpty(message = "You must supply a value for the Description.")
    @Length(max = 50, message = "Description must be no more than 100 characters in length.")
    private String heroDescr;
    @NotEmpty(message = "You must supply a value for the the Hero Power.")
    @Length(max = 50, message = "Hero Power must be no more than 50 characters in length.")
    private String heroPower;
    private Organization org;
    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocation(List<Location> locations) {
        this.locations = locations;
    }
    
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }
    
    public long getHeroId() {
        return heroId;
    }

    public void setHeroId(long heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroDescr() {
        return heroDescr;
    }

    public void setHeroDescr(String heroDescr) {
        this.heroDescr = heroDescr;
    }

    public String getHeroPower() {
        return heroPower;
    }

    public void setHeroPower(String heroPower) {
        this.heroPower = heroPower;
    }
   
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (int) (this.heroId ^ (this.heroId >>> 32));
        hash = 23 * hash + Objects.hashCode(this.heroName);
        hash = 23 * hash + Objects.hashCode(this.heroDescr);
        hash = 23 * hash + Objects.hashCode(this.heroPower);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Heroes other = (Heroes) obj;
        if (this.heroId != other.heroId) {
            return false;
        }
        if (!Objects.equals(this.heroName, other.heroName)) {
            return false;
        }
        if (!Objects.equals(this.heroDescr, other.heroDescr)) {
            return false;
        }
        if (!Objects.equals(this.heroPower, other.heroPower)) {
            return false;
        }
        return true;
    }
}
