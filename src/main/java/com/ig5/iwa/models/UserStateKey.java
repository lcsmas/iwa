package com.ig5.iwa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserStateKey implements Serializable {

    @Column(name = "id_user")
    int id_user;

    @Column(name = "id_state")
    int id_state;

    public UserStateKey(int id_user, int id_state) {
        this.id_user = id_user;
        this.id_state = id_state;
    }

    public UserStateKey() { }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_state() {
        return id_state;
    }

    public void setId_state(int id_state) {
        this.id_state = id_state;
    }
}
