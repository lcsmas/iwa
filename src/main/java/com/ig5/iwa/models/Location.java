package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity(name="location")
@Table(name = "location", schema = "public")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_location;
    private float longitude;
    private float latitude;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<User_Localized> users;

    @OneToMany(mappedBy="location", cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.EXCEPTION)
    private Set<Notification> notifications;

    //@JsonManagedReference
    @JsonBackReference
    public Set<User_Localized> getUsers() {
        return users;
    }

    //@JsonManagedReference
    @JsonBackReference
    public Set<Notification> getNotifications() {
        return notifications;
    }

    public Location() {}

    public Location(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(int id, float longitude, float latitude) {
        this.id_location = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public void setUsers(Set<User_Localized> users) {
        this.users = users;
    }

    public void setId_Location(Integer id_location) { this.id_location = id_location; }

    public Integer getId_Location() { return id_location; }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public Integer getId_location() {
        return id_location;
    }

    public void setId_location(Integer id_location) {
        this.id_location = id_location;
    }



    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }


}
