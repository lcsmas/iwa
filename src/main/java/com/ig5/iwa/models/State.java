package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name="state")
@Table(name = "state", schema = "public")
@Access(AccessType.FIELD)
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_state;

    private String label_state;

    public State() { }

    public State(String label_state){
        this.label_state = label_state;
    }

    public State(int id, String label_state) {
        this.id_state = id;
        this.label_state = label_state;
    }

    @OneToMany(mappedBy = "state")
    private Set<User_State> users;

    @OneToMany(mappedBy = "state" , cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.EXCEPTION)
    private Set<Notification> notifications;

    //@JsonManagedReference
    @JsonBackReference
    public Set<User_State> getUsers() {
        return users;
    }

    //@JsonManagedReference
    @JsonBackReference
    public Set<Notification> getNotifications() {
        return notifications;
    }

    public Integer getId_state() {
        return id_state;
    }

    public void setId_state(Integer id_state) {
        this.id_state = id_state;
    }

    public String getLabel_state() {
        return label_state;
    }

    public void setLabel_state(String label_state) {
        this.label_state = label_state;
    }

    public void setUsers(Set<User_State> users) {
        this.users = users;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }


}
