package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.entity.EmployeeEntity;
import com.codeventlk.helloshoemanagementsystem.entity.StockEntity;
import com.codeventlk.helloshoemanagementsystem.repository.StockServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class StockServiceIMPL implements StockService {

    private final StockServiceDao stockServiceDao;
    @Override
    public String getStockId() {
        return getNextStockId();
    }

    public String getNextStockId(){
        StockEntity stockEntity = stockServiceDao.findFirstByOrderByStockIdDesc();
        return (stockEntity != null)
                ? String.format("St-%03d",
                Integer.parseInt(stockEntity.getStockId()
                        .replace("St-", "")) + 1)
                : "St-001";
    }
}
