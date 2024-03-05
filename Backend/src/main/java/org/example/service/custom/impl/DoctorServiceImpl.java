package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.DoctorDto;
import org.example.dto.PetDto;
import org.example.dto.StaffDto;
import org.example.entity.CustomerEntity;
import org.example.entity.DoctorEntity;
import org.example.entity.PetEntity;
import org.example.entity.StaffEntity;
import org.example.repository.DoctorRepository;
import org.example.service.custom.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    DoctorRepository repository;
    @Override
    public DoctorEntity save(DoctorDto dto) {
        DoctorEntity entity=
                mapper.convertValue(dto,DoctorEntity.class);
        return repository.save(entity);
    }

    @Override
    public boolean delete(Long value) {
        Optional<DoctorEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<DoctorDto> getAll() {
        List<DoctorDto> list=new ArrayList<>();

        Iterable<DoctorEntity> doctorList=repository.findAll();
        Iterator<DoctorEntity> iterator=doctorList.iterator();
        while(iterator.hasNext()){
            DoctorEntity entity=iterator.next();


            DoctorDto dto=mapper.convertValue(entity,DoctorDto.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        Iterable<DoctorEntity> doctorList=repository.findAll();
        Iterator<DoctorEntity> iterator=doctorList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            DoctorEntity entity=iterator.next();

            lastId= entity.getDoctorId();

        }
        return lastId+1;
    }

    @Override
    public DoctorDto getById(Long id) {
        return mapper.convertValue(repository.findById(id), DoctorDto.class) ;
    }
}
