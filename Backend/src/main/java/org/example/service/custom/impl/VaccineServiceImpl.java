package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.VaccineDto;
import org.example.entity.VaccineEntity;
import org.example.repository.VaccineRepository;
import org.example.service.custom.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineServiceImpl implements VaccineService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    VaccineRepository repository;

    @Override
    public VaccineEntity save(VaccineDto dto) {
        VaccineEntity entity=
                mapper.convertValue(dto,VaccineEntity.class);
        return repository.save(entity);
    }

    @Override
    public boolean delete(Long value) {
        Optional<VaccineEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<VaccineDto> getAll() {
        List<VaccineDto> list=new ArrayList<>();

        Iterable<VaccineEntity> vaccineList=repository.findAll();
        Iterator<VaccineEntity> iterator=vaccineList.iterator();
        while(iterator.hasNext()){
            VaccineEntity entity=iterator.next();
            VaccineDto dto=mapper.convertValue(entity,VaccineDto.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        Iterable<VaccineEntity> vaccineList=repository.findAll();
        Iterator<VaccineEntity> iterator=vaccineList.iterator();

        Long lastId=null;
        while(iterator.hasNext()){
            VaccineEntity entity=iterator.next();
            lastId= entity.getVaccineId();
        }
        return lastId+1;
    }

    @Override
    public VaccineDto getById(Long id) {
        return mapper.convertValue(repository.findById(id), VaccineDto.class) ;
    }
}
