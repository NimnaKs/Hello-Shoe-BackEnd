package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "StockSizeOrderDetails")
public class StockSizeOrderDetailsEntity {
    @Id
    private String stockSizeOrderDetailsId;
    private int qty;

    @ManyToOne
    @JoinColumn(name = "stockId",nullable = false)
    private StockEntity stockEntity;

    @ManyToOne
    @JoinColumn(name = "orderId",nullable = false)
    private OrderEntity orderEntity;
}
