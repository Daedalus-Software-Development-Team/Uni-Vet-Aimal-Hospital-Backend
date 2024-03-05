package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PresDetailAndMedCombinedDto;
import org.example.dto.PrescriptionDto;
import org.example.entity.MedicineEntity;
import org.example.entity.PrescriptionDetailEntity;
import org.example.entity.PrescriptionEntity;
import org.example.repository.MedicineRepository;
import org.example.repository.PrescriptionDetailRepository;
import org.example.repository.PrescriptionRepository;
import org.example.service.custom.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    PrescriptionRepository repository;
    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    PrescriptionDetailRepository prescriptionDetailRepository;

    @Override
    public PrescriptionEntity save(PrescriptionDto dto) {
        PrescriptionEntity prescriptionEntity=new PrescriptionEntity();
        prescriptionEntity.setPrescriptionId(dto.getPrescriptionId());
        prescriptionEntity.setDescription(dto.getDescription());
        prescriptionEntity.setCustomerId(dto.getCustomerId());
        prescriptionEntity.setTotal(dto.getTotal());
        prescriptionEntity.setDoctorId(dto.getDoctorId());

        PrescriptionEntity savedPrescriptionEntity =repository.save(prescriptionEntity);
        //        System.out.println(dto.getPrescriptionDetailArray());

        for (PresDetailAndMedCombinedDto dto1: dto.getPrescriptionDetailArray()) {
            MedicineEntity medicineEntity=new MedicineEntity(
                    dto1.getMedicineId(),
                    dto1.getMedicineName(),
                    Double.parseDouble(dto1.getPrice())
            );
            MedicineEntity savedMedicineEntity=medicineRepository.save(medicineEntity);


            PrescriptionDetailEntity prescriptionDetailEntity=new PrescriptionDetailEntity(
                    dto1.getPrescriptionDetailId(),
                    savedPrescriptionEntity.getPrescriptionId(),
                    savedMedicineEntity.getMedicineId(),
                    dto1.getAvailable(),
                    dto1.getBeforeMeal() ,
                    Double.parseDouble(dto1.getDailyQuantity()),
                    Integer.parseInt(dto1.getDays()),
                    Double.parseDouble(dto1.getPrice()),
                    dto1.getDosage()
            );
            prescriptionDetailRepository.save(prescriptionDetailEntity);

        }


        return savedPrescriptionEntity;
    }

    @Override
    public boolean delete(Long value) {
        Optional<PrescriptionEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<PrescriptionDto> getAll() {
        List<PrescriptionDto> list=new ArrayList<>();

        Iterable<PrescriptionEntity> entityList=repository.findAll();
        Iterator<PrescriptionEntity> iterator=entityList.iterator();
        while(iterator.hasNext()){
            PrescriptionEntity entity=iterator.next();

            PrescriptionDto dto=mapper.convertValue(entity,PrescriptionDto.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        Iterable<PrescriptionEntity> entityList = repository.findAll();
        Iterator<PrescriptionEntity> iterator = entityList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            PrescriptionEntity entity=iterator.next();
            lastId= entity.getPrescriptionId();
        }
        return lastId+1;
    }

    @Override
    public PrescriptionDto getById(Long id) {
        return mapper.convertValue(repository.findById(id), PrescriptionDto.class);
    }
}
