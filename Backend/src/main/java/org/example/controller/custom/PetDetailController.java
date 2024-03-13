package org.example.controller.custom;

import org.example.controller.CrudController;
import org.example.dto.PetDetailDto;
import org.example.entity.PetDetailEntity;

import java.util.List;

public interface PetDetailController extends CrudController<PetDetailEntity,PetDetailDto> {
    List<PetDetailDto> getPetDetailByPetId(Long id);
}
