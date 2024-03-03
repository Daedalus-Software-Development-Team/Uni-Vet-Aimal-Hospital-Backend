package org.example.controller.custom.impl;

import org.example.controller.custom.PrescriptionDetailController;
import org.example.dto.PrescriptionDetailDto;
import org.example.dto.Response;
import org.example.entity.PrescriptionDetailEntity;
import org.example.service.custom.PetService;
import org.example.service.custom.PrescriptionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PrescriptionDetailControllerImpl implements PrescriptionDetailController {

    @Autowired
    PrescriptionDetailService service;

    @PostMapping("/prescriptionDetail")
    @Override
    public PrescriptionDetailEntity saveOrUpdate(@RequestBody PrescriptionDetailDto dto) {
        return service.save(dto);
    }

    @GetMapping("/prescriptionDetail")
    @Override
    public List<PrescriptionDetailDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @Override
    @DeleteMapping("/prescriptionDetail/{prescriptionDetailId}")
    public Response delete(@PathVariable Long prescriptionDetailId) {
        boolean isRemoved = service.delete(prescriptionDetailId);
        if(isRemoved) {
            return new Response(String.format("Removed Prescription Detail Id (%s)", prescriptionDetailId));

        } else {
            return new Response(String.format("Prescription Detail Id (%s) Invalid", prescriptionDetailId));

        }
    }

    @GetMapping("/prescriptionDetailNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }

    @GetMapping("/prescriptionDetail/{prescriptionDetailId}")
    @Override
    public PrescriptionDetailDto getById(@PathVariable Long prescriptionDetailId) {
        return service.getById(prescriptionDetailId);
    }
}
