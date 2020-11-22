package com.ig5.iwa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserStateKey implements Serializable {

    @Column(name = "id_user")
    int idUser;

    @Column(name = "id_state")
    int idState;

    public UserStateKey(int id_user, int id_state) {
        this.idUser = id_user;
        this.idState = id_state;
    }

    public UserStateKey() { }

    public int getId_user() {
        return idUser;
    }

    public void setId_user(int id_user) {
        this.idUser = id_user;
    }

    public int getId_state() {
        return idState;
    }

    public void setId_state(int id_state) {
        this.idState = id_state;
    }
}
