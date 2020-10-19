package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="Location")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Location;

//    @ManyToMany(mappedBy = "Location" )
//    @JsonIgnore // Pour ne pas produire des cycles
//    private List<User > users;
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    public void setId_Location(Integer id_location) {
        this.id_Location = id_location;
    }

    public Integer getId_Location() {
        return id_Location;
    }
}
