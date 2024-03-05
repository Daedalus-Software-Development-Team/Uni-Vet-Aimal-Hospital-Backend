package org.example.controller.custom.impl;

import org.example.controller.custom.MedicineController;
import org.example.dto.MedicineDto;
import org.example.dto.Response;
import org.example.entity.MedicineEntity;
import org.example.service.custom.CustomerService;
import org.example.service.custom.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
public class MedicineControllerImpl implements MedicineController {
    @Autowired
    MedicineService service;

    @PostMapping("/medicine")
    @Override
    public MedicineEntity saveOrUpdate(@RequestBody MedicineDto dto) {
        return service.save(dto);
    }

    @GetMapping("/medicine")
    @Override
    public List<MedicineDto> getAll() throws InterruptedException {
        return service.getAll();
    }
    @Override
    @DeleteMapping("/medicine/{medicineId}")
    public Response delete(@PathVariable Long medicineId) {
        boolean isRemoved = service.delete(medicineId);
        if(isRemoved) {
            return new Response(String.format("Removed Medicine Id (%s)", medicineId));

        } else {
            return new Response(String.format("Medicine Id (%s) Invalid", medicineId));

        }
    }

    @GetMapping("/medicineNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }

    @GetMapping("/medicine/{medicineId}")
    @Override
    public MedicineDto getById(@PathVariable Long medicineId) {
        return service.getById(medicineId);
    }
}
