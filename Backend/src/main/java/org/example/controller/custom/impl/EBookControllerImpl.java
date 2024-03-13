package org.example.controller.custom.impl;

import org.example.dto.PetDetailDto;
import org.example.dto.PresDetailAndMedCombinedDto;
import org.example.dto.PrescriptionDto;
import org.example.dto.Response;
import org.example.service.custom.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin
public class EBookControllerImpl {
    @Autowired
    EbookService ebookService;
    @GetMapping("/ebook/prescriptions/{petId}")
    public List<PrescriptionDto> getPrescriptionsById(@PathVariable Long petId){
        return ebookService.getPrescriptionsById(petId);
    }
    @GetMapping("/ebook/petDetails/{petId}")
    public List<PetDetailDto> getPetDetailsByPetId(@PathVariable Long petId){
        return ebookService.getPetDetailsByPetId(petId);
    }
    @GetMapping("/ebook/print/{petId}")
    public Response printBook(@PathVariable Long petId){
        return new Response(ebookService.printBook(petId));
    }

}
