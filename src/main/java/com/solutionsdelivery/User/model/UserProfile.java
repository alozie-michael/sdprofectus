package com.solutionsdelivery.User.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "USERPROFILE")
@Data
public class UserProfile {

    @Id
    @Column(name = "STAFFNO")
    private String staffNo;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "ROLES")
    private String roles;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "CREATEDDATE")
    private String createdDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userProfile")
    private UserLogin userLogin;

}
