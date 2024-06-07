package com.codeventlk.helloshoemanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StockDTO implements SuperDTO{
    @NotBlank(message = "Supplier Id cannot be blank")
    private String supplierId;
    @NotBlank(message = "Item Id cannot be blank")
    private String itemId;
    @NotBlank(message = "Size Id cannot be blank")
    private String sizeId;

    @PositiveOrZero(message = "Quantity must be positive or zero")
    private int quantity;

    @PositiveOrZero(message = "Unit Buying Price must be positive or zero")
    private Double unitBuyingPrice;

    @PositiveOrZero(message = "Unit Selling Price must be positive or zero")
    private Double unitSellingPrice;
}
