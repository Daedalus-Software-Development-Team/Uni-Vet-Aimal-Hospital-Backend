package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PetDetailDto;
import org.example.entity.PetDetailEntity;
import org.example.repository.PetDetailRepository;
import org.example.service.custom.PetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PetDetailServiceImpl implements PetDetailService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    PetDetailRepository repository;

    @Override
    public PetDetailEntity save(PetDetailDto dto) {
        PetDetailEntity entity=
                mapper.convertValue(dto,PetDetailEntity.class);
        return repository.save(entity);
    }

    @Override
    public boolean delete(Long value) {
        Optional<PetDetailEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<PetDetailDto> getAll() {
        List<PetDetailDto> list=new ArrayList<>();

        Iterable<PetDetailEntity> petDetailList=repository.findAll();
        Iterator<PetDetailEntity> iterator=petDetailList.iterator();
        while(iterator.hasNext()){
            PetDetailEntity entity=iterator.next();


            PetDetailDto dto=mapper.convertValue(entity,PetDetailDto.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        Iterable<PetDetailEntity> petDetailList=repository.findAll();
        Iterator<PetDetailEntity> iterator=petDetailList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            PetDetailEntity entity=iterator.next();

            lastId= entity.getPetDetailId();

        }
        return lastId+1;
    }

    @Override
    public PetDetailDto getById(Long id) {
        return mapper.convertValue(repository.findById(id), PetDetailDto.class) ;
    }
}
