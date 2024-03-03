package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PrescriptionDto;
import org.example.entity.PrescriptionEntity;
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

    @Override
    public PrescriptionEntity save(PrescriptionDto dto) {
        PrescriptionEntity entity= mapper.convertValue(dto,PrescriptionEntity.class);
        return repository.save(entity);
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
