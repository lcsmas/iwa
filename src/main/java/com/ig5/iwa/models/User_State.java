package com.ig5.iwa.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User_State {

    @EmbeddedId
    UserStateKey id;

    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    User user;

    @ManyToOne
    @MapsId("id_state")
    @JoinColumn(name = "id_state")
    State state;

    Date date;

    public UserStateKey getId() {
        return id;
    }

    public void setId(UserStateKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
