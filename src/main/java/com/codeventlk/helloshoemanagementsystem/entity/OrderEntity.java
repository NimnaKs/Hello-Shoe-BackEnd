package com.codeventlk.helloshoemanagementsystem.entity;

import com.codeventlk.helloshoemanagementsystem.Enum.PaymentMethod;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Orders")
public class OrderEntity {
    @Id
    private String orderNo;
    private Timestamp purchasedDate;
    private int addedPoints;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "cutomerId",nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "email",nullable = false)
    private UserEntity userEntity;
}
