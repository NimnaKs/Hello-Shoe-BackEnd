package com.codeventlk.helloshoemanagementsystem.entity;

import com.codeventlk.helloshoemanagementsystem.Enum.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "Orders")
@Data
public class OrderEntity {
    @Id
    private String orderNo;
    private Timestamp purchasedDate;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private double totalAmount;
    private double paidAmount;
    private String bankName;
    private String bankNo;

    @ManyToOne
    @JoinColumn(name = "cutomerId",nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "email",nullable = false)
    private UserEntity userEntity;

}
