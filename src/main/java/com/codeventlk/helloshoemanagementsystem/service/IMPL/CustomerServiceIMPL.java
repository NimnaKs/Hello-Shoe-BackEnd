package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.CustomerDTO;
import com.codeventlk.helloshoemanagementsystem.entity.CustomerEntity;
import com.codeventlk.helloshoemanagementsystem.entity.Level;
import com.codeventlk.helloshoemanagementsystem.repository.CustomerServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceIMPL implements CustomerService {

    final private ConversionData conversionData;

    final private CustomerServiceDao customerServiceDao;
    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(getNextCustomerId());
        customerDTO.setLevel(Level.NEW);
        customerDTO.setTotalPoint(0);
        CustomerEntity customerEntity = conversionData.convertToCustomerEntity(customerDTO);
        customerServiceDao.save(customerEntity);
        return customerServiceDao.existsById(customerServiceDao.findFirstByOrderByCustomerIdDesc().getCustomerId());
    }

    private String getNextCustomerId() {
        CustomerEntity firstByOrderByCustomerIdDesc = customerServiceDao.findFirstByOrderByCustomerIdDesc();
        return (firstByOrderByCustomerIdDesc != null) ?
                String.format("Cust-%03d",
                        Integer.parseInt(firstByOrderByCustomerIdDesc.getCustomerId().replace("Cust-", "")) + 1) :
                "Cust-001";
    }
}
