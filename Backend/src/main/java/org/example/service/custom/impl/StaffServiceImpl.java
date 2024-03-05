package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.StaffDto;
import org.example.entity.StaffEntity;
import org.example.repository.StaffRepository;
import org.example.service.custom.StaffService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class StaffServiceImpl implements StaffService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    StaffRepository repository;

    @Override
    public StaffEntity save(StaffDto dto) {
        StaffEntity entity=
                mapper.convertValue(dto,StaffEntity.class);
        return repository.save(entity);
    }

    @Override
    public boolean delete(Long value) {
        Optional<StaffEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<StaffDto> getAll() {
        List<StaffDto> list=new ArrayList<>();

        Iterable<StaffEntity> StaffList=repository.findAll();
        Iterator<StaffEntity> iterator=StaffList.iterator();
        while(iterator.hasNext()){
            StaffEntity entity=iterator.next();
            StaffDto dto=mapper.convertValue(entity,StaffDto.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        Iterable<StaffEntity> staffList=repository.findAll();
        Iterator<StaffEntity> iterator=staffList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            StaffEntity entity=iterator.next();

            lastId= entity.getStaffId();

        }
        return lastId+1;
    }

    @Override
    public StaffDto getById(Long id) {
        return mapper.convertValue(repository.findById(id), StaffDto.class) ;
    }
}
