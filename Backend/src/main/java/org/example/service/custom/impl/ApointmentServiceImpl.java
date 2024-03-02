package org.example.service.custom.impl;

import org.example.dto.ApointmentDto;
import org.example.entity.ApointmentEntity;
import org.example.entity.CustomerEntity;
import org.example.service.custom.ApointmentSevice;

import java.sql.SQLException;
import java.util.List;

public class ApointmentServiceImpl implements ApointmentSevice {

    @Override
    public ApointmentEntity save(ApointmentDto dto) {
        return null;
    }

    @Override
    public boolean delete(Long value) {
        return false;
    }

    @Override
    public List<ApointmentDto> getAll() {
        return null;
    }

    @Override
    public Long getNextId() throws SQLException {
        return null;
    }

    @Override
    public ApointmentDto getById(Long id) {
        return null;
    }
}
