/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dto;

import java.time.LocalDate;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Chad
 */
public class Sighting {
    private int sightingId;
    @NotEmpty(message = "You must supply a value for the Hero Sighted.")
    private long heroId;
    @NotEmpty(message = "You must supply a value for the Location of sighting.")
    private long locationId;
    @NotEmpty(message = "You must supply a value for the Sighting Date.")
    private LocalDate sightingDate;
    
    public int getSightingId() {
        return sightingId;
    }
    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }
    public long getHeroId() {
        return heroId;
    }
    public void setHeroId(long heroId) {
        this.heroId = heroId;
    }
    public long getLocationId() {
        return locationId;
    }
    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }
    public LocalDate getSightingDate() {
        return sightingDate;
    }
    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.sightingId;
        hash = 41 * hash + (int) (this.heroId ^ (this.heroId >>> 32));
        hash = 41 * hash + (int) (this.locationId ^ (this.locationId >>> 32));
        hash = 41 * hash + Objects.hashCode(this.sightingDate);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (this.heroId != other.heroId) {
            return false;
        }
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.sightingDate, other.sightingDate)) {
            return false;
        }
        return true;
    }

    
}