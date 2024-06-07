package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table (name = "Stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity {

    @Id
    private String stockId;

    private Date supplierOrderDate;
    private int qty;
    private Double unitBuyingPrice;
    private Double unitSellingPrice;
    private int availableQty;

    @ManyToOne
    @JoinColumn(name = "supplierCode",nullable = false)
    private SupplierEntity supplierEntity;

    @ManyToOne
    @JoinColumn(name = "itemCode",nullable = false)
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(name = "branchId",nullable = false)
    private BranchEntity branchEntity;

    @ManyToOne
    @JoinColumn(name = "sizeCode",nullable = false)
    private SizeEntity sizeEntity;

}
