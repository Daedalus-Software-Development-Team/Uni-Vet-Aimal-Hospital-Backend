package org.example.controller.custom.impl;

import org.example.controller.custom.PrescriptionController;
import org.example.dto.PrescriptionDto;
import org.example.dto.Response;
import org.example.entity.PrescriptionEntity;
import org.example.service.custom.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PrescriptionControllerImpl implements PrescriptionController {

    @Autowired
    PrescriptionService service;

    @PostMapping("/prescription")
    @Override
    public PrescriptionEntity saveOrUpdate(@RequestBody PrescriptionDto dto) {
        return service.save(dto);
    }

    @GetMapping("/prescription")
    @Override
    public List<PrescriptionDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @Override
    @DeleteMapping("/prescription/{prescriptionId}")
    public Response delete(@PathVariable Long prescriptionId) {
        boolean isRemoved = service.delete(prescriptionId);
        if(isRemoved) {
            return new Response(String.format("Removed Prescription Id (%s)", prescriptionId));

        } else {
            return new Response(String.format("Prescription Id (%s) Invalid", prescriptionId));

        }
    }

    @GetMapping("/prescriptionNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }

    @GetMapping("/prescription/{prescriptionId}")
    @Override
    public PrescriptionDto getById(@PathVariable Long prescriptionId) {
        return service.getById(prescriptionId);
    }
}
