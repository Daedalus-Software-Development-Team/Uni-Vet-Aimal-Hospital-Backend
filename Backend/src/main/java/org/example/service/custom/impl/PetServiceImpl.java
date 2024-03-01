package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PetDto;
import org.example.entity.PetEntity;
import org.example.repository.PetRepository;
import org.example.service.custom.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Service
public class PetServiceImpl implements PetService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    PetRepository repository;


    @Override
    public PetEntity save(PetDto dto) {
        PetEntity entity=
                mapper.convertValue(dto,PetEntity.class);
        return repository.save(entity);
    }



    @Override
    public boolean delete(Long value) {
        Optional<PetEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<PetDto> getAll() {
        List<PetDto> list=new ArrayList<>();

        Iterable<PetEntity> petList=repository.findAll();
        Iterator<PetEntity> iterator=petList.iterator();
        while(iterator.hasNext()){
            PetEntity entity=iterator.next();


            PetDto dto=mapper.convertValue(entity,PetDto.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {

        Iterable<PetEntity> petList=repository.findAll();
        Iterator<PetEntity> iterator=petList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            PetEntity entity=iterator.next();

            lastId= entity.getPetId();

        }
        return lastId+1;
    }
}
