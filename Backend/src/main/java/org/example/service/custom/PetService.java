package org.example.service.custom;

import org.example.dto.PetDto;
import org.example.entity.PetEntity;
import org.example.service.CrudService;

public interface PetService extends CrudService<PetEntity, PetDto> {
}
