package org.example.controller.custom.impl;

import org.example.controller.custom.PetDetailController;
import org.example.dto.PetDetailDto;
import org.example.dto.Response;
import org.example.entity.PetDetailEntity;
import org.example.service.custom.PetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PetDetailControllerImpl implements PetDetailController {

    @Autowired
    PetDetailService service;

    @PostMapping("/petDetail")
    @Override
    public PetDetailEntity saveOrUpdate(@RequestBody PetDetailDto dto) {
        return service.save(dto);
    }

    @GetMapping("/petDetail")
    @Override
    public List<PetDetailDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @DeleteMapping("/petDetail/{petDetailId}")
    @Override
    public Response delete(@PathVariable Long petDetailId) {
        boolean isRemoved = service.delete(petDetailId);
        if(isRemoved) {
            return new Response(String.format("Removed Pet Detail Id (%s)", petDetailId));

        } else {
            return new Response(String.format("Pet Detail Id (%s) Invalid", petDetailId));

        }
    }

    @GetMapping("/petDetailNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }

    @GetMapping("/petDetail/{petDetailId}")
    @Override
    public PetDetailDto getById(@PathVariable Long petDetailId) {
        return service.getById(petDetailId);
    }
    @GetMapping("/petDetail/petId/{id}")
    @Override
    public List<PetDetailDto> getPetDetailByPetId(@PathVariable Long id) {
        List<PetDetailDto> dtos=service.getAll();
        List<PetDetailDto> list=new ArrayList<>();
        for (PetDetailDto dto:dtos) {
            if(dto.getPetId()==id){
                list.add(dto);
            }
        }
        return list;
    }
}
