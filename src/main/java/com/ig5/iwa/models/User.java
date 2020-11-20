package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name="user")
@Table(name = "user", schema = "public")
@Access(AccessType.FIELD)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    private String mail;
    private String password;

    public User(){}

    public User(Integer id_user, String mail, String password) {
        this.id_user = id_user;
        this.mail = mail;
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    private Set<User_State> states;

    @OneToMany(mappedBy = "user")
    private Set<User_Localized> locations;

    public Set<User_State> getStates() { return states; }

    public void setStates(Set<User_State> states) { this.states = states; }

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
