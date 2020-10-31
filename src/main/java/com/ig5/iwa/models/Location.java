package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="location")
@Table(name = "location", schema = "public")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Location;
    float longitude;
    float latitude;

    @ManyToMany(mappedBy = "locations" )
    @JsonIgnore // Pour ne pas produire des cycles
    private List<User > users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setId_Location(Integer id_location) { this.id_Location = id_location; }

    public Integer getId_Location() { return id_Location; }

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
