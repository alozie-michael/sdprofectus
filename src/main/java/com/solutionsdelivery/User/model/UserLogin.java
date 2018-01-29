package com.solutionsdelivery.User.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "USERLOGIN")
@Data
public class UserLogin {

    @Id
    @Column(name = "STAFFNO")
    private String staffNo;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LASTLOGIN")
    private String lastLogin;

    @Column(name = "LASTPASSWORDUPDATE")
    private String lastPasswordUpdate;

    @OneToOne
    private UserProfile userProfile;

}
