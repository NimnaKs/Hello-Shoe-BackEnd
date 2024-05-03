package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.ItemDTO;
import com.codeventlk.helloshoemanagementsystem.entity.GenderEntity;
import com.codeventlk.helloshoemanagementsystem.entity.ItemEntity;
import com.codeventlk.helloshoemanagementsystem.entity.OccasionEntity;
import com.codeventlk.helloshoemanagementsystem.entity.VarietyEntity;
import com.codeventlk.helloshoemanagementsystem.repository.GenderServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.ItemServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.OccasionServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.VarietyServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceIMPL implements ItemService {
    private final ItemServiceDao itemServiceDao;
    private final ConversionData conversionData;
    private final GenderServiceDao genderServiceDao;
    private final OccasionServiceDao occasionServiceDao;
    private final VarietyServiceDao varietyServiceDao;
    @Override
    public void saveItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = conversionData.toItemEntity(itemDTO);
        itemEntity.setItemCode(generateItemCode(itemDTO));
        Optional<GenderEntity> genderEntity = genderServiceDao.findById(itemDTO.getGenderCode());
        if (genderEntity.isPresent()){
            GenderEntity genderEntity1 = genderEntity.get();
            itemEntity.setGenderEntity(genderEntity1);
        };
        Optional<OccasionEntity> occasionEntity = occasionServiceDao.findById(itemDTO.getOccasionCode());
        if (occasionEntity.isPresent()){
            OccasionEntity occasionEntity1 = occasionEntity.get();
            itemEntity.setOccasionEntity(occasionEntity1);
        }
        Optional<VarietyEntity> varietyEntity = varietyServiceDao.findById(itemDTO.getVarietyCode());
        if (varietyEntity.isPresent()){
            VarietyEntity varietyEntity1 = varietyEntity.get();
            itemEntity.setVarietyEntity(varietyEntity1);
        }
        itemServiceDao.save(itemEntity);
    }

    private String generateItemCode(ItemDTO itemDTO) {

        String prefix = itemDTO.getGenderCode() != null
                ? itemDTO.getGenderCode()
                : itemDTO.getOccasionCode() != null
                ? itemDTO.getOccasionCode()
                : itemDTO.getVarietyCode();

        String lastItemCodeStartingWithPrefix =
                itemServiceDao.findLastItemCodeStartingWithPrefix(prefix);

        return (lastItemCodeStartingWithPrefix != null)
                ? String.format(prefix+"%05d",
                Integer.parseInt(lastItemCodeStartingWithPrefix.
                        replace(prefix, "")) + 1)
                : prefix+"00001";
    }
}