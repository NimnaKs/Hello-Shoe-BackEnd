package com.codeventlk.helloshoemanagementsystem.repository;

import com.codeventlk.helloshoemanagementsystem.entity.StockEntity;
import com.codeventlk.helloshoemanagementsystem.entity.StockSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockSizeServiceDao extends JpaRepository<StockSizeEntity,String> {

}
