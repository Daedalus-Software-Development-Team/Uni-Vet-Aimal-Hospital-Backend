package org.example.controller.custom.impl;

import org.example.controller.custom.VaccineController;
import org.example.dto.Response;
import org.example.dto.VaccineDto;
import org.example.entity.VaccineEntity;
import org.example.service.custom.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class VaccineControllerImpl implements VaccineController{
    @Autowired
    VaccineService service;

    @PostMapping("/vaccine")
    @Override
    public VaccineEntity saveOrUpdate(@RequestBody VaccineDto dto) {
        return service.save(dto);
    }

    @GetMapping("/vaccine")
    @Override
    public List<VaccineDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @Override
    @DeleteMapping("/vaccine/{vaccineId}")
    public Response delete(@PathVariable Long vaccineId) {
        boolean isRemoved = service.delete(vaccineId);
        if(isRemoved) {
            return new Response(String.format("Removed Vaccine Id (%s)", vaccineId));
        } else {
            return new Response(String.format("Vaccine Id (%s) Invalid", vaccineId));

        }
    }

    @GetMapping("/vaccineNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }

    @GetMapping("/vaccine/{vaccineId}")
    @Override
    public VaccineDto getById(@PathVariable Long vaccineId) {
        return service.getById(vaccineId);
    }
}
