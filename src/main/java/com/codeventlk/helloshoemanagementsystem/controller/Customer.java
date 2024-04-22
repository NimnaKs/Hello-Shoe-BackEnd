package com.codeventlk.helloshoemanagementsystem.controller;

import com.codeventlk.helloshoemanagementsystem.dto.CustomerDTO;
import com.codeventlk.helloshoemanagementsystem.entity.Gender;
import com.codeventlk.helloshoemanagementsystem.entity.Level;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class Customer {

    @GetMapping("/health")
    public String healthCheck(){
        return "Customer Health Check";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@Validated @RequestBody CustomerDTO customerDTO,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Customer saved successfully."+customerDTO);
    }

}
