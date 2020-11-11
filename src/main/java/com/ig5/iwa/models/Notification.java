package com.ig5.iwa.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name="notification")
@Table(name = "notification", schema = "public")
@Access(AccessType.FIELD)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_notification;
    private String label_notification;
    private Timestamp date_notification;

    @OneToMany
    @JoinTable(name="concerned",
            joinColumns = @JoinColumn(name="id_notification"),
            inverseJoinColumns = @JoinColumn(name="id_location"))
    private List<Location> locations;

    public Integer getId_notification() {
        return id_notification;
    }

    public void setId_notification(Integer id_notification) {
        this.id_notification = id_notification;
    }

    public String getLabel_notification() {
        return label_notification;
    }

    public void setLabel_notification(String label_notification) {
        this.label_notification = label_notification;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Timestamp getDate_notification() {
        return date_notification;
    }

    public void setDate_notification(Timestamp date_notification) {
        this.date_notification = date_notification;
    }
}
