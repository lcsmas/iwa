package com.ig5.iwa.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name="state")
@Table(name = "state", schema = "public")
@Access(AccessType.FIELD)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_state;
    private String label_state;

    public State() { }

    public State(String label_state){
        this.label_state = label_state;
    }

    @OneToMany(mappedBy = "state")
    private Set<User_State> users;

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

    public Set<User_State> getUsers() {
        return users;
    }

    public void setUsers(Set<User_State> users) {
        this.users = users;
    }
}
