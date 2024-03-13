package org.example.controller.custom.impl;

import org.example.controller.custom.StaffController;
import org.example.dto.Response;
import org.example.dto.StaffDto;
import org.example.entity.StaffEntity;
import org.example.service.custom.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class StaffControllerImpl implements StaffController {

    @Autowired
    StaffService service;

    @PostMapping("/staff")
    @Override
    public StaffEntity saveOrUpdate(@RequestBody StaffDto dto) {
        return service.save(dto);
    }

    @GetMapping("/staff")
    @Override
    public List<StaffDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @Override
    @DeleteMapping("/staff/{staffId}")
    public Response delete(@PathVariable Long staffId) {
        boolean isRemoved = service.delete(staffId);
        if(isRemoved) {
            return new Response(String.format("Removed Staff Id (%s)", staffId));

        } else {
            return new Response(String.format("Staff Id (%s) Invalid", staffId));

        }
    }

    @GetMapping("/staffNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }

    @GetMapping("/staff/{staffId}")
    @Override
    public StaffDto getById(@PathVariable Long staffId) {
        return service.getById(staffId);
    }
}
