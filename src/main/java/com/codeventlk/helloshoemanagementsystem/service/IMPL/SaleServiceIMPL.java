package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.Enum.Level;
import com.codeventlk.helloshoemanagementsystem.Enum.PaymentMethod;
import com.codeventlk.helloshoemanagementsystem.dto.OrderDTO;
import com.codeventlk.helloshoemanagementsystem.dto.OrderDetailsDTO;
import com.codeventlk.helloshoemanagementsystem.dto.StockOrderDetailsServiceDao;
import com.codeventlk.helloshoemanagementsystem.entity.CustomerEntity;
import com.codeventlk.helloshoemanagementsystem.entity.OrderEntity;
import com.codeventlk.helloshoemanagementsystem.entity.StockEntity;
import com.codeventlk.helloshoemanagementsystem.entity.StockOrderDetailsEntity;
import com.codeventlk.helloshoemanagementsystem.repository.CustomerServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.SaleServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.StockServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.UserServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.CustomerService;
import com.codeventlk.helloshoemanagementsystem.service.SaleService;
import com.codeventlk.helloshoemanagementsystem.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class SaleServiceIMPL implements SaleService {

    private final SaleServiceDao saleServiceDao;

    private final CustomerServiceDao customerServiceDao;

    private final UserServiceDao userServiceDao;

    private final StockServiceDao stockServiceDao;

    private final StockOrderDetailsServiceDao stockOrderDetailsServiceDao;
    @Override
    public String getNextOrderId() {
        return getOrderId();
    }

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderNo(orderDTO.getOrderNo());
        orderEntity.setPurchasedDate(new Timestamp(System.currentTimeMillis()));
        orderEntity.setPaymentMethod(orderDTO.getPaymentMethod());
        orderEntity.setTotalAmount(orderDTO.getTotalAmount());
        orderEntity.setPaidAmount(orderDTO.getPaidAmount());
        orderEntity.setBankName(orderDTO.getBankName());
        orderEntity.setBankNo(orderDTO.getBankNo());

        CustomerEntity customerEntity = customerServiceDao.findById(orderDTO.getCustomerId()).orElseThrow();
        orderEntity.setCustomerEntity(customerEntity);

        orderEntity.setUserEntity(userServiceDao.findByEmail(orderDTO.getUserId()).get());

        saleServiceDao.save(orderEntity);

        for (OrderDetailsDTO detailDTO : orderDTO.getOrderDetailsList()) {
            StockOrderDetailsEntity stockOrderDetailsEntity = new StockOrderDetailsEntity();
            stockOrderDetailsEntity.setStockOrderDetailsId(UUID.randomUUID().toString());
            stockOrderDetailsEntity.setQty(detailDTO.getQty());

            StockEntity stockEntity = stockServiceDao.findById(detailDTO.getStockId()).orElseThrow();
            stockOrderDetailsEntity.setStockEntity(stockEntity);
            stockOrderDetailsEntity.setOrderEntity(orderEntity);

            stockOrderDetailsServiceDao.save(stockOrderDetailsEntity);

            stockEntity.setAvailableQty(stockEntity.getAvailableQty() - detailDTO.getQty());
            stockServiceDao.save(stockEntity);
        }

        customerEntity.setTotalPoint(customerEntity.getTotalPoint() + 1);
        customerEntity.setRecentPurchasedDate(new Timestamp(System.currentTimeMillis()));
        updateCustomerLevel(customerEntity);
    }

    private void updateCustomerLevel(CustomerEntity customerEntity) {
        int points = customerEntity.getTotalPoint();
        if (points < 50) {
            customerEntity.setLevel(Level.NEW);
        } else if (points <= 99) {
            customerEntity.setLevel(Level.BRONZE);
        } else if (points <= 199) {
            customerEntity.setLevel(Level.SILVER);
        } else {
            customerEntity.setLevel(Level.GOLD);
        }
        customerServiceDao.save(customerEntity);
    }
    private String getOrderId() {
        OrderEntity order = saleServiceDao.findFirstByOrderByOrderNoDesc();
        return (order != null)
                ? String.format("Order-%03d",
                Integer.parseInt(order.getOrderNo().
                        replace("Order-", "")) + 1)
                : "Order-001";
    }
}
