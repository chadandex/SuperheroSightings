/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dto;

import java.time.LocalDate;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Chad
 */
public class Location {
    
    private long locationId;
    @NotEmpty(message = "You must supply a value for the the Location Name.")
    @Length(max = 50, message = "Location Description must be no more than 50 characters in length.")
    private String locationName;
    @NotEmpty(message = "You must supply a value for the the Location Description.")
    @Length(max = 100, message = "Location Description must be no more than 100 characters in length.")
    private String locationDescr;
    @NotEmpty(message = "You must supply a value for the the Location Address.")
    @Length(max = 50, message = "Location Address must be no more than 50 characters in length.")
    private String locationAddress;
    @NotEmpty(message = "You must supply a value for the the Location Latitude.")
    @Length(max = 20, message = "Location Latitude must be no more than 20 characters in length.")
    private String locationLat;
    @NotEmpty(message = "You must supply a value for the the Location Longitude.")
    @Length(max = 20, message = "Location Longitude must be no more than 20 characters in length.")
    private String locationLong;

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescr() {
        return locationDescr;
    }

    public void setLocationDescr(String locationDescr) {
        this.locationDescr = locationDescr;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(String locationLong) {
        this.locationLong = locationLong;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.locationId ^ (this.locationId >>> 32));
        hash = 59 * hash + Objects.hashCode(this.locationDescr);
        hash = 59 * hash + Objects.hashCode(this.locationAddress);
        hash = 59 * hash + Objects.hashCode(this.locationLat);
        hash = 59 * hash + Objects.hashCode(this.locationLong);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.locationDescr, other.locationDescr)) {
            return false;
        }
        if (!Objects.equals(this.locationAddress, other.locationAddress)) {
            return false;
        }
        if (!Objects.equals(this.locationLat, other.locationLat)) {
            return false;
        }
        if (!Objects.equals(this.locationLong, other.locationLong)) {
            return false;
        }
        return true;
    }

}
