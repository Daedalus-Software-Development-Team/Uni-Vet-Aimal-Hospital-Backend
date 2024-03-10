package org.example.service.custom.impl;

import org.example.dto.AppointmentDto;
import org.example.entity.AppointmentEntity;
import org.example.service.custom.ApointmentSevice;

import java.sql.SQLException;
import java.util.List;

public class ApointmentServiceImpl implements ApointmentSevice {

    @Override
    public AppointmentEntity save(AppointmentDto dto) {
        return null;
    }

    @Override
    public boolean delete(Long value) {
        return false;
    }

    @Override
    public List<AppointmentDto> getAll() {
        return null;
    }

    @Override
    public Long getNextId() throws SQLException {
        return null;
    }

    @Override
    public AppointmentDto getById(Long id) {
        return null;
    }
}
