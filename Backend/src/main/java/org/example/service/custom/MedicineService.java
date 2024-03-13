package org.example.service.custom;

import org.example.dto.DoctorDto;
import org.example.dto.MedicineDto;
import org.example.entity.DoctorEntity;
import org.example.entity.MedicineEntity;
import org.example.service.CrudService;

public interface MedicineService extends CrudService<MedicineEntity, MedicineDto> {
}
