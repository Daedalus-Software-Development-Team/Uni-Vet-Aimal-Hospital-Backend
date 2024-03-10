package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AppointmentDto;
import org.example.entity.AppointmentEntity;
import org.example.repository.AppointmentRepository;
import org.example.service.custom.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    AppointmentRepository repository;

    @Override
    public AppointmentEntity save(AppointmentDto dto) {
        AppointmentEntity entity=
                mapper.convertValue(dto,AppointmentEntity.class);
        return repository.save(entity);
    }

    @Override
    public boolean delete(Long value) {
        Optional<AppointmentEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<AppointmentDto> getAll() {
        List<AppointmentDto> list=new ArrayList<>();

        Iterable<AppointmentEntity> appointmentList=repository.findAll();
        Iterator<AppointmentEntity> iterator=appointmentList.iterator();
        while(iterator.hasNext()){
            AppointmentEntity entity=iterator.next();
            AppointmentDto dto=mapper.convertValue(entity,AppointmentDto.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        Iterable<AppointmentEntity> appointmentList=repository.findAll();
        Iterator<AppointmentEntity> iterator=appointmentList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            AppointmentEntity entity=iterator.next();

            lastId= entity.getAppointmentId();

        }
        return (lastId+1);
    }

    @Override
    public AppointmentDto getById(Long id) {
        return mapper.convertValue(repository.findById(id), AppointmentDto.class) ;
    }
}
