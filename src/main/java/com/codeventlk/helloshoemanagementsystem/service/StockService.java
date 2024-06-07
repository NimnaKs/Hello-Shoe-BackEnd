package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.StockDTO;

import java.util.List;

public interface StockService {
    String getStockId();

    void saveStock(StockDTO stockDTO, String email);

    List<StockDTO> getAllStock();
}
