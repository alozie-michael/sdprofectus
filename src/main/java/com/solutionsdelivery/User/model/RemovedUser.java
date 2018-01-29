package com.solutionsdelivery.User.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "REMOVEDUSER")
@Data
public class RemovedUser {

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

    @Column(name = "REMOVEDBY")
    private String removedBy;

    @Column(name = "REMOVEDDATE")
    private String removedDate;

}
