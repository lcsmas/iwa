package com.ig5.iwa.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
public class User_Localized {

    @EmbeddedId
    private UserLocalizedKey id = new UserLocalizedKey();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("id_user")
    @JoinColumn(name = "id_user", columnDefinition = "INT")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("id_location")
    @JoinColumn(name = "id_location", columnDefinition = "INT")
    private Location location;

    private Date date = Date.from(Instant.now());

    //@JsonBackReference
    @JsonManagedReference
    public User getUser() {
        return user;
    }

    //@JsonBackReference
    @JsonManagedReference
    public Location getLocation() {
        return location;
    }

    public User_Localized(){}

    public User_Localized(User user, Location location) {
        this.user = user;
        this.location = location;
        this.id.setId_user(user.getId_user());
        this.id.setId_location(location.getId_Location());
    }

    public UserLocalizedKey getId() {
        return id;
    }

    public void setId(UserLocalizedKey id) {
        this.id = id;
    }



    public void setUser(User user) {
        this.user = user;
    }



    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
