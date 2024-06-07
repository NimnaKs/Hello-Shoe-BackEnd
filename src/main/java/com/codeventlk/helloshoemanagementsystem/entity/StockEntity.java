package com.codeventlk.helloshoemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "Stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity {

    @Id
    private String stockId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "supplierCode",nullable = false)
    private SupplierEntity supplierEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "itemCode",nullable = false)
    private ItemEntity itemEntity;
    private Date supplierOrderDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "branchId",nullable = false)
    private BranchEntity branchEntity;

}
