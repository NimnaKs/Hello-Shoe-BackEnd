package com.codeventlk.helloshoemanagementsystem.entity;

import com.codeventlk.helloshoemanagementsystem.Enum.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "Item")
public class ItemEntity {
    @Id
    private String itemCode;
    private String itemDesc;
    private String pic;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "genderCode",nullable = false)
    private GenderEntity genderEntity;

}
