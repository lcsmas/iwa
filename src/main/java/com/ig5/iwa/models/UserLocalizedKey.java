package com.ig5.iwa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserLocalizedKey implements Serializable {

    @Column(name = "id_user")
    int idUser;

    @Column(name = "id_location")
    int idLocation;

    public int getId_user() {
        return idUser;
    }

    public void setId_user(int id_user) {
        this.idUser = id_user;
    }

    public int getId_location() {
        return idLocation;
    }

    public void setId_location(int id_location) {
        this.idLocation = id_location;
    }
}
