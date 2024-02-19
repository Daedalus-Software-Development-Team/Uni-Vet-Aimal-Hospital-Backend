package org.example.controller.custom;

import org.example.controller.CrudController;
import org.example.dto.PetDto;
import org.example.entity.PetEntity;

public interface PetController extends CrudController<PetEntity, PetDto> {
}
