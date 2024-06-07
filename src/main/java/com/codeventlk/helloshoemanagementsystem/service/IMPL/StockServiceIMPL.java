package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.StockDTO;
import com.codeventlk.helloshoemanagementsystem.entity.*;
import com.codeventlk.helloshoemanagementsystem.repository.*;
import com.codeventlk.helloshoemanagementsystem.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockServiceIMPL implements StockService {

    private final StockServiceDao stockServiceDao;

    private final SupplierServiceDao supplierServiceDao;

    private final ItemServiceDao itemServiceDao;

    private final SizeServiceDao sizeServiceDao;

    private final EmployeeServiceDao employeeServiceDao;

    private final BranchServiceDao branchServiceDao;

    private final ConversionData conversionData;

    @Override
    public String getStockId() {
        return getNextStockId();
    }

    @Override
    public void saveStock(StockDTO stockDTO, String email) {

        EmployeeEntity byEmail = employeeServiceDao.findByEmail(email);
        Optional<BranchEntity> branch = branchServiceDao.findById(byEmail.getBranch().getBranchId());
        BranchEntity branchEntity = branch.get();
        SizeEntity sizeEntity = sizeServiceDao.findById(stockDTO.getSizeId()).get();

        stockServiceDao.save(new StockEntity(
                getNextStockId(),
                new Date(),
                stockDTO.getQuantity(),
                stockDTO.getUnitBuyingPrice(),
                stockDTO.getUnitSellingPrice(),
                supplierServiceDao.findById(stockDTO.getSupplierId()).get(),
                itemServiceDao.findById(stockDTO.getItemId()).get(),
                branchEntity,
                sizeEntity
        ));

    }

    @Override
    public List<StockDTO> getAllStock() {
        return conversionData.toStockEntity(stockServiceDao.findAll());
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
