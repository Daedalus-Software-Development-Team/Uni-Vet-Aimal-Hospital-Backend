package org.example.controller.custom;

import org.example.controller.CrudController;
import org.example.dto.CustomerDto;
import org.example.dto.DoctorDto;
import org.example.entity.CustomerEntity;
import org.example.entity.DoctorEntity;

public interface DoctorController  extends CrudController<DoctorEntity, DoctorDto> {
}
