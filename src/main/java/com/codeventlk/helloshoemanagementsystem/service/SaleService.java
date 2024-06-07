package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.OrderDTO;
import org.springframework.stereotype.Repository;

public interface SaleService {
    String getNextOrderId();

    void saveOrder(OrderDTO orderDTO);
}
