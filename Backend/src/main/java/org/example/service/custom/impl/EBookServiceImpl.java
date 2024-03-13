package org.example.service.custom.impl;

import org.example.dto.PetDetailDto;
import org.example.dto.PrescriptionDto;
import org.example.service.custom.EbookService;
import org.example.service.custom.PetDetailService;
import org.example.service.custom.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EBookServiceImpl implements EbookService {
    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    PetDetailService petDetailService;
    @Override
    public List<PrescriptionDto> getPrescriptionsById(Long petId) {
        List<PrescriptionDto> list=new ArrayList<>();
        List<PrescriptionDto> all=prescriptionService.getAll();
        for (PrescriptionDto dto:all) {
            if(dto.getPetId().equals(petId)){
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public List<PetDetailDto> getPetDetailsByPetId(Long petId) {
        List<PetDetailDto> all=petDetailService.getAll();
        List<PetDetailDto> list=new ArrayList<>();
        for (PetDetailDto dto:all) {
            if(dto.getPetId().equals(petId)){
                list.add(dto);
            }
        }
        return list;
    }
}
