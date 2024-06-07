package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "StockOrderDetails")
@Data
public class StockOrderDetailsEntity {
    @Id
    private String stockOrderDetailsId;
    private int qty;

    @ManyToOne
    @JoinColumn(name = "stockId",nullable = false)
    private StockEntity stockEntity;

    @ManyToOne
    @JoinColumn(name = "orderId",nullable = false)
    private OrderEntity orderEntity;
}
