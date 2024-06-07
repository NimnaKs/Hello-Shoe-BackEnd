package com.codeventlk.helloshoemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "StockSize")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockSizeEntity {

    @Id
    private String stockSizeId;
    private int qty;
    private Double unitBuyingPrice;
    private Double unitSellingPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stockId",nullable = false)
    private StockEntity stockEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sizeCode",nullable = false)
    private SizeEntity sizeEntity;
}
