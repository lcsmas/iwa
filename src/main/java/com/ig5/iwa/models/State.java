package com.ig5.iwa.models;

import javax.persistence.*;
import java.util.List;

@Entity(name="state")
@Table(name = "state", schema = "public")
@Access(AccessType.FIELD)
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_state;
    private String label_state;

    @ManyToMany
    @JoinTable(name="user_state",
            joinColumns = @JoinColumn(name="id_state"),
            inverseJoinColumns = @JoinColumn(name="id_user"))
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
