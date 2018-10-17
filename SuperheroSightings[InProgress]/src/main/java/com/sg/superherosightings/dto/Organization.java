/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dto;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Chad
 */
public class Organization {
    
    private long orgId;
    @NotEmpty(message = "You must supply a value for the Organization Name.")
    @Length(max = 50, message = "Email must be no more than 50 characters in length.")
    private String orgName;
    @NotEmpty(message = "You must supply a value for the Description.")
    @Length(max = 50, message = "Description must be no more than 100 characters in length.")
    private String orgDescr;
    @NotEmpty(message = "You must supply a value for the Address.")
    @Length(max = 50, message = "Address must be no more than 50 characters in length.")
    private String orgAddress;
    @NotEmpty(message = "You must supply a value for the Phone Number.")
    @Length(max = 50, message = "Phone must be no more than 10 characters in length.")
    private String orgPhone;
    @NotEmpty(message = "You must supply a value for the Email.")
    @Email(message = "Please enter a valid email address.")
    @Length(max = 50, message = "Email must be no more than 50 characters in length.")
    private String orgEmail;
    private List<Heroes> hero;

    public List<Heroes> getHeroes() {
        return hero;
    }

    public void setHeroes(List<Heroes> members) {
        this.hero = members;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescr() {
        return orgDescr;
    }

    public void setOrgDescr(String orgDescr) {
        this.orgDescr = orgDescr;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }
  
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (this.orgId ^ (this.orgId >>> 32));
        hash = 71 * hash + Objects.hashCode(this.orgName);
        hash = 71 * hash + Objects.hashCode(this.orgDescr);
        hash = 71 * hash + Objects.hashCode(this.orgAddress);
        hash = 71 * hash + Objects.hashCode(this.orgPhone);
        hash = 71 * hash + Objects.hashCode(this.orgEmail);
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
        final Organization other = (Organization) obj;
        if (this.orgId != other.orgId) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDescr, other.orgDescr)) {
            return false;
        }
        if (!Objects.equals(this.orgAddress, other.orgAddress)) {
            return false;
        }
        if (!Objects.equals(this.orgPhone, other.orgPhone)) {
            return false;
        }
        if (!Objects.equals(this.orgEmail, other.orgEmail)) {
            return false;
        }
        return true;
    }
}
