/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weather.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zhout
 */
@Entity
@Table(name = "user_saved_places")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSavedPlaces.findAll", query = "SELECT u FROM UserSavedPlaces u")
    , @NamedQuery(name = "UserSavedPlaces.findByUsername", query = "SELECT u FROM UserSavedPlaces u WHERE u.username = :username")
    , @NamedQuery(name = "UserSavedPlaces.findBySavedPlace", query = "SELECT u FROM UserSavedPlaces u WHERE u.savedPlace = :savedPlace")})
public class UserSavedPlaces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "saved_place")
    private String savedPlace;

    public UserSavedPlaces() {
    }

    public UserSavedPlaces(String username) {
        this.username = username;
    }

    public UserSavedPlaces(String username, String savedPlace) {
        this.username = username;
        this.savedPlace = savedPlace;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSavedPlace() {
        return savedPlace;
    }

    public void setSavedPlace(String savedPlace) {
        this.savedPlace = savedPlace;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSavedPlaces)) {
            return false;
        }
        UserSavedPlaces other = (UserSavedPlaces) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.weather.db.UserSavedPlaces[ username=" + username + " ]";
    }
    
}
