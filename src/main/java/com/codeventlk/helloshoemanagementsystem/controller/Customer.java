package com.codeventlk.helloshoemanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class Customer {

    @GetMapping("/healthCheck")
    public String healthCheck(){
        return "Customer Health Check";
    }
}
