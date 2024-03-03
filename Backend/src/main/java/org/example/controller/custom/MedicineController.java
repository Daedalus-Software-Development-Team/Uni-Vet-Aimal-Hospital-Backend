package org.example.controller.custom;

import org.example.controller.CrudController;
import org.example.dto.MedicineDto;
import org.example.dto.PetDto;
import org.example.entity.MedicineEntity;
import org.example.entity.PetEntity;

public interface MedicineController extends CrudController<MedicineEntity, MedicineDto> {
}
