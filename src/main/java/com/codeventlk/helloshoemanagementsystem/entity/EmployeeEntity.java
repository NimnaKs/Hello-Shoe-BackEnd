package com.codeventlk.helloshoemanagementsystem.entity;

import com.codeventlk.helloshoemanagementsystem.Enum.Gender;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table (name = "Employee")
public class EmployeeEntity {
    @Id
    private String employeeCode;
    private String employeeName;
    private String profilePic;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String status;

    private String designation;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    private Date dateOfJoin;

    private String attachedBranch;

    private String address1;

    private String address2;

    private String address3;

    private String address4;
    private String postalCode;
    private String contactNo;
    private String email;
    private String emergencyContactName;
    private String emergencyContact;
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;
}
