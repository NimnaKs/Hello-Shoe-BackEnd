package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockServiceDao extends JpaRepository<StockEntity,String> {
    StockEntity findFirstByOrderByStockIdDesc();
}
