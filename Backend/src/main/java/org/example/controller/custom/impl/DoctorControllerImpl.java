package org.example.controller.custom.impl;

import org.example.controller.custom.DoctorController;
import org.example.dto.DoctorDto;
import org.example.dto.Response;
import org.example.entity.DoctorEntity;
import org.example.service.custom.CustomerService;
import org.example.service.custom.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
public class DoctorControllerImpl implements DoctorController {
    @Autowired
    DoctorService service;
    @PostMapping("/doctor")
    @Override
    public DoctorEntity saveOrUpdate(@RequestBody DoctorDto dto) {
        return service.save(dto);
    }

    @GetMapping("/doctor")
    @Override
    public List<DoctorDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @Override
    @DeleteMapping("/doctor/{doctorId}")
    public org.example.dto.Response delete(@PathVariable Long doctorId)  {
        boolean isRemoved = service.delete(doctorId);
        if(isRemoved) {
            return new Response(String.format("Removed Doctor Id (%s)", doctorId));

        } else {
            return new Response(String.format("Doctor Id (%s) Invalid", doctorId));

        }
    }

    @GetMapping("/doctorNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }
}
