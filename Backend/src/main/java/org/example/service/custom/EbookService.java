package org.example.service.custom;

import org.example.dto.PetDetailDto;
import org.example.dto.PrescriptionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EbookService {
    public List<PrescriptionDto> getPrescriptionsById(Long petId);

    public List<PetDetailDto> getPetDetailsByPetId(Long petId);
}
