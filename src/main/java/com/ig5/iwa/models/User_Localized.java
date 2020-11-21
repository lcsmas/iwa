package com.ig5.iwa.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User_Localized {

    @EmbeddedId
    UserLocalizedKey id;

    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    User user;

    @ManyToOne
    @MapsId("id_location")
    @JoinColumn(name = "id_location")
    Location location;

    Date date;

}
