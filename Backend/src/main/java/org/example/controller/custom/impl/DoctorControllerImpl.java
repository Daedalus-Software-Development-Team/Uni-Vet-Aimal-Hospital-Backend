package org.example.controller.custom.impl;

import org.example.controller.custom.DoctorController;
import org.example.dto.DoctorDto;
import org.example.dto.Response;
import org.example.entity.DoctorEntity;
import org.example.service.custom.CustomerService;
import org.example.service.custom.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin
public class DoctorControllerImpl implements DoctorController {
    @Autowired
    DoctorService service;
    @PostMapping("/doctor")
    @Override
    public DoctorEntity saveOrUpdate(DoctorDto dto) {
        return service.save(dto);
    }

    @Override
    public List<DoctorDto> getAll() throws InterruptedException {
        return null;
    }

    @Override
    public Response delete(Long value) {
        return null;
    }

    @Override
    public Long getNextId() throws Exception {
        return null;
    }
}
