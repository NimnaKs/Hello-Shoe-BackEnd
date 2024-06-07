package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.StockDTO;

public interface StockService {
    String getStockId();

    void saveStock(StockDTO stockDTO, String email);
}
