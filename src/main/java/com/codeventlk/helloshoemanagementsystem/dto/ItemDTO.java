package com.codeventlk.helloshoemanagementsystem.dto;

import com.codeventlk.helloshoemanagementsystem.Enum.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class ItemDTO implements SuperDTO{
    @Null(message = "Item code generate by the program")
    private String itemCode;
    @NotBlank(message = "Item Description cannot be blank")
    private String itemDesc;
    @NotNull(message = "Propic cannot be null")
    private String pic;
    @NotNull(message = "Status cannot be null")
    private Status status;
    @NotNull(message = "Category cannot be null")
    private String category;

}
