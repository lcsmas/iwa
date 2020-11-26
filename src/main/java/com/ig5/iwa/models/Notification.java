package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity(name="notification")
@Table(name = "notification", schema = "public")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_notification;

    private String label_notification;

    @Column(name = "date_notification")
    private Timestamp dateNotification = Timestamp.from(Instant.now());


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location")
    //@JsonIgnoreProperties("users")
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    //@JsonIgnoreProperties("states")

    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_state")
    //@JsonIgnoreProperties("users")
    private State state;

    //@JsonBackReference
    @JsonManagedReference
    public Location getLocation() {
        return location;
    }

    //@JsonBackReference
    @JsonManagedReference
    public User getUser() {
        return user;
    }

    //@JsonBackReference
    @JsonManagedReference
    public State getState() {
        return state;
    }

    public Notification(String label_notification) {
        this.label_notification = label_notification;
    }

    public Notification(){}

    public Integer getId_notification() {
        return id_notification;
    }

    public void setId_notification(Integer id_notification) {
        this.id_notification = id_notification;
    }

    public Timestamp getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Timestamp dateNotification) {
        this.dateNotification = dateNotification;
    }

    public String getLabel_notification() {
        return label_notification;
    }

    public void setLabel_notification(String label_notification) {
        this.label_notification = label_notification;
    }

    public Timestamp getDate_notification() {
        return dateNotification;
    }

    public void setDate_notification(Timestamp date_notification) {
        this.dateNotification = date_notification;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setState(State state) {
        this.state = state;
    }
}
