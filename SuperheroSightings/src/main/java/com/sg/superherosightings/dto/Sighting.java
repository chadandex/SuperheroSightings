/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dto;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Chad
 */
public class Sighting {
    @NotNull
    private int sightingId;
    @NotNull
    private int heroId;
    @NotNull
    private int locationId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate sightingDate;
    
    public int getSightingId() {
        return sightingId;
    }
    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }
    public int getHeroId() {
        return heroId;
    }
    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }
    public int getLocationId() {
        return locationId;
    }
    public void setLocationId(int locationId) {
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