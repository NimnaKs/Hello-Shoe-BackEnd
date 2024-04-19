package com.codeventlk.helloshoemanagementsystem.dto;

import com.codeventlk.helloshoemanagementsystem.entity.Gender;
import com.codeventlk.helloshoemanagementsystem.entity.Level;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements SuperDTO{

    @Null(message = "Generate by the program")
    private String customerId;
    @NotNull(message = "Customer cannot be null")
    private String customerName;
    @NotNull(message = "Gender cannot be null")
    private Gender gender;
    @NotNull(message = "Level cannot be null")
    private Level level;
    @NotNull(message = "Join Date cannot be null")
    private Date joinDate;
    @NotNull(message = "Total points cannot be null")
    private Integer totalPoint;
    @NotNull(message = "Date of Birth cannot be null")
    private Date dob;
    @NotNull(message = "Address cannot be null")
    private String address;

}
