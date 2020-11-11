package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity(name="user")
@Table(name = "user", schema = "public")
@Access(AccessType.FIELD)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    private String mail;
    private String password;

    @ManyToMany
    @JoinTable(name="user_localized",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name="id_location"))
    @JsonManagedReference
    private List<Location> locations;

    @ManyToMany
    @JoinTable(name="user_state",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name="id_state"))
    @JsonManagedReference
    private List<State> states;

    public List<State> getStates() { return states; }

    public void setStates(List<State> states) { this.states = states; }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId_user(Integer id) {
        this.id_user = id;
    }

    public Integer getId_user() {
        return id_user;
    }
}
