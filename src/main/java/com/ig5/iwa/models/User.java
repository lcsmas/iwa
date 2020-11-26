package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity(name="user")
@Table(name = "user", schema = "public")
@Access(AccessType.FIELD)
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String mail;
    private String password;

    public User(){}

    public User(Integer id_user, String mail, String password) {
        this.idUser = id_user;
        this.mail = mail;
        this.password = password;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<User_State> states;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<User_Localized> locations;


    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.EXCEPTION)
    private Set<Notification> notifications;

    //@JsonManagedReference
    @JsonBackReference
    public Set<User_State> getStates() { return states; }

    //@JsonManagedReference
    @JsonBackReference
    public Set<User_Localized> getLocations() {
        return locations;
    }

    //@JsonManagedReference
    @JsonBackReference
    public Set<Notification> getNotifications() {
        return notifications;
    }
    public void addUserState(User_State user_state){
        states.add(user_state);
    }

    public void addUserLocation(User_Localized user_localized) {
        locations.add(user_localized);
    }

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
        this.idUser = id;
    }

    public Integer getId_user() {
        return idUser;
    }

    public void setLocations(Set<User_Localized> locations) {
        this.locations = locations;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }


}
