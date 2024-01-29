package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.DoctorDto;
import org.example.entity.CustomerEntity;
import org.example.entity.DoctorEntity;
import org.example.repository.DoctorRepository;
import org.example.service.custom.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public DoctorEntity save(DoctorDto dto) {
        DoctorEntity entity=
                mapper.convertValue(dto,DoctorEntity.class);
        return doctorRepository.save(entity);
    }

    @Override
    public boolean delete(Long value) {
        return false;
    }

    @Override
    public List<DoctorDto> getAll() {
        return null;
    }

    @Override
    public Long getNextId() throws SQLException {
        return null;
    }
}
