package com.solutionsdelivery.OTP.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OTPREQUESTLOGS")
public @lombok.Data class OtpRequestLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STARTTIME")
    private String startTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "otpRequestLogs")
    @Column(name = "DATA")
    private List<Data> data;

}
