package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.CustomerEntity;
import com.codeventlk.helloshoemanagementsystem.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeServiceDao extends JpaRepository<EmployeeEntity,String> {
    EmployeeEntity findFirstByOrderByEmployeeCodeDesc();

    EmployeeEntity findByEmail(String email);
}
