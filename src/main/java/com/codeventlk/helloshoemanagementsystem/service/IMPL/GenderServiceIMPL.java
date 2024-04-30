package com.codeventlk.helloshoemanagementsystem.service.IMPL;

import com.codeventlk.helloshoemanagementsystem.conversion.ConversionData;
import com.codeventlk.helloshoemanagementsystem.dto.GenderDTO;
import com.codeventlk.helloshoemanagementsystem.entity.GenderEntity;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.repository.GenderServiceDao;
import com.codeventlk.helloshoemanagementsystem.repository.InventoryServiceDao;
import com.codeventlk.helloshoemanagementsystem.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class GenderServiceIMPL implements GenderService {

    private final GenderServiceDao genderServiceDao;
    private final ConversionData conversionData;
    @Override
    public void saveGender(GenderDTO genderDTO) {
       genderServiceDao.save(conversionData.toGenderEntity(genderDTO));
    }

    @Override
    public List<GenderDTO> getAllGenders() {
        return conversionData.convertToGenderDTO(genderServiceDao.findAll());
    }

    @Override
    public void deleteGender(String id) {
        if (!genderServiceDao.existsById(id)) throw new NotFoundException("Gender Not Found");
       genderServiceDao.deleteById(id);
    }

    @Override
    public void updateGender(String id, GenderDTO genderDTO) {
        if (!genderServiceDao.existsById(id)) throw new NotFoundException("Gender Not Found");
        Optional<GenderEntity> byId = genderServiceDao.findById(id);
        GenderEntity genderEntity = byId.get();
        genderEntity.setGenderCode(genderDTO.getGenderCode());
        genderEntity.setGenderDesc(genderDTO.getGenderDesc());

    }


}
