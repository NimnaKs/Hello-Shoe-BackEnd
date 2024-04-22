package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    private String customerId;

    private String customerName;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Temporal(TemporalType.DATE)
    private Date joinDate;
    private Integer totalPoint;

    @Temporal(TemporalType.DATE)
    private Date dob;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String postalCode;

}
