package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleServiceDao extends JpaRepository<OrderEntity,String> {
    OrderEntity findFirstByOrderByOrderNoDesc();
}
