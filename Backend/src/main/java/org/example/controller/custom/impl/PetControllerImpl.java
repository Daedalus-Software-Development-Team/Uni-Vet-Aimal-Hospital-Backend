package org.example.controller.custom.impl;

import org.example.controller.custom.PetController;
import org.example.dto.PetDto;
import org.example.dto.Response;
import org.example.entity.PetEntity;
import org.example.service.custom.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PetControllerImpl implements PetController {

    @Autowired
    PetService service;

    @PostMapping("/pet")
    @Override
    public PetEntity saveOrUpdate(@RequestBody PetDto dto)  {
        return service.save(dto);
    }
    @GetMapping("/pet")
    @Override
    public List<PetDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @Override
    @DeleteMapping("/pet/{petId}")
    public Response delete(@PathVariable Long petId)  {
        boolean isRemoved = service.delete(petId);
        if(isRemoved) {
            return new Response(String.format("Removed Pet Id (%s)", petId));

        } else {
            return new Response(String.format("Pet Id (%s) Invalid", petId));

        }
    }

    @GetMapping("/petNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }

    @GetMapping("/pet/{petId}")
    @Override
    public PetDto getById(@PathVariable Long petId) {
        return service.getById(petId);
    }
}
