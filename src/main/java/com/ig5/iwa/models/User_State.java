package com.ig5.iwa.models;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
public class User_State {

    @EmbeddedId
    private UserStateKey id = new UserStateKey();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("id_state")
    @JoinColumn(name = "id_state")
    private State state;

    private Date date = Date.from(Instant.now());

    public User_State(){}

    public User_State(User user, State state) {
        this.user = user;
        this.state = state;
        this.id.setId_user(user.getId_user());
        this.id.setId_state(state.getId_state());
    }

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
