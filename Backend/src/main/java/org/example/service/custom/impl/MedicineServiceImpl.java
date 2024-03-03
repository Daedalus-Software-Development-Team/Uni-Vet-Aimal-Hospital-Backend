package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CustomerDto;
import org.example.dto.MedicineDto;
import org.example.entity.CustomerEntity;
import org.example.entity.MedicineEntity;
import org.example.repository.CustomerRepository;
import org.example.repository.MedicineRepository;
import org.example.service.custom.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public MedicineEntity save(MedicineDto dto) {


        MedicineEntity entity= mapper.convertValue(dto,MedicineEntity.class);

        return medicineRepository.save(entity);
    }

    @Override
    public boolean delete(Long value) {
        Optional<MedicineEntity> medicineEntityOptional
                =medicineRepository.findById(value);
        if(medicineEntityOptional.isPresent()){
            medicineRepository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<MedicineDto> getAll() {
        List<MedicineDto> list=new ArrayList<>();

        Iterable<MedicineEntity> medicineList=medicineRepository.findAll();
        Iterator<MedicineEntity> iterator=medicineList.iterator();
        while(iterator.hasNext()){
            MedicineEntity entity=iterator.next();


            MedicineDto medicineDto=mapper.convertValue(entity,MedicineDto.class);
            list.add(medicineDto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        List<MedicineDto> list=new ArrayList<>();

        Iterable<MedicineEntity> medicineList=medicineRepository.findAll();
        Iterator<MedicineEntity> iterator=medicineList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            MedicineEntity entity=iterator.next();

            lastId= entity.getMedicineId();

        }
        return lastId+1;
    }

    @Override
    public MedicineDto getById(Long id) {
        return mapper.convertValue(medicineRepository.findById(id),MedicineDto.class) ;
    }
}
