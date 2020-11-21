package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

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
    @JsonIgnore
    private Set<User_Localized> users;

    public Location() {}

    public Location(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.id_location = Math.abs(UUID.randomUUID().hashCode());
    }

    public Location(int id, float longitude, float latitude) {
        this.id_location = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Set<User_Localized> getUsers() {
        return users;
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

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
