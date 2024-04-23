package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "StockSize")
public class StockSizeEntity {

    @Id
    private String stockSizeId;
    private int qty;
    private Double unitBuyingPrice;
    private Double unitSellingPrice;
    private Double profit;
    private Double profitMargin;

    @ManyToOne
    @JoinColumn(name = "stockId",nullable = false)
    private StockEntity stockEntity;

    @ManyToOne
    @JoinColumn(name = "sizeCode",nullable = false)
    private SizeEntity sizeEntity;
}
