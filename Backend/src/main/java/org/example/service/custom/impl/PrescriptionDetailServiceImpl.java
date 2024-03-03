package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PrescriptionDetailDto;
import org.example.entity.PrescriptionDetailEntity;
import org.example.repository.PrescriptionDetailRepository;
import org.example.service.custom.PrescriptionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionDetailServiceImpl implements PrescriptionDetailService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    PrescriptionDetailRepository repository;

    @Override
    public PrescriptionDetailEntity save(PrescriptionDetailDto dto) {
        PrescriptionDetailEntity entity= mapper.convertValue(dto,PrescriptionDetailEntity.class);
        return repository.save(entity);
    }

    @Override
    public boolean delete(Long value) {
        Optional<PrescriptionDetailEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<PrescriptionDetailDto> getAll() {
        List<PrescriptionDetailDto> list=new ArrayList<>();

        Iterable<PrescriptionDetailEntity> entityList=repository.findAll();
        Iterator<PrescriptionDetailEntity> iterator=entityList.iterator();
        while(iterator.hasNext()){
            PrescriptionDetailEntity entity=iterator.next();

            PrescriptionDetailDto dto=mapper.convertValue(entity,PrescriptionDetailDto.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        Iterable<PrescriptionDetailEntity> entityList = repository.findAll();
        Iterator<PrescriptionDetailEntity> iterator = entityList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            PrescriptionDetailEntity entity=iterator.next();
            lastId= entity.getPrescriptionDetailId();
        }
        return lastId+1;
    }

    @Override
    public PrescriptionDetailDto getById(Long id) {
        return mapper.convertValue(repository.findById(id), PrescriptionDetailDto.class);
    }
}
