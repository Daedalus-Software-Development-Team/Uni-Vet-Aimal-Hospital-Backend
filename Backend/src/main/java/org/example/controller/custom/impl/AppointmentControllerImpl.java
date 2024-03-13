package org.example.controller.custom.impl;

import org.example.controller.custom.AppointmentController;
import org.example.dto.AppointmentDto;
import org.example.dto.Response;
import org.example.entity.AppointmentEntity;
import org.example.service.custom.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AppointmentControllerImpl implements AppointmentController {
    @Autowired
    AppointmentService service;

    @PostMapping("/app")
    @Override
    public AppointmentEntity saveOrUpdate(@RequestBody AppointmentDto dto) {
        return service.save(dto);
    }

    @GetMapping("/app")
    @Override
    public List<AppointmentDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @Override
    @DeleteMapping("/app/{appId}")
    public Response delete(@PathVariable Long appId) {
        boolean isRemoved = service.delete(appId);
        if(isRemoved) {
            return new Response(String.format("Removed Appointment Id (%s)", appId));

        } else {
            return new Response(String.format("Appointment Id (%s) Invalid", appId));

        }
    }

    @GetMapping("/appNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }

    @GetMapping("/app/{appId}")
    @Override
    public AppointmentDto getById(@PathVariable Long appId) {
        return service.getById(appId);
    }
}
