package org.example.service.custom;

import org.example.dto.CustomerDto;
import org.example.dto.DoctorDto;
import org.example.entity.DoctorEntity;
import org.example.service.CrudService;

public interface DoctorService extends CrudService<DoctorEntity,DoctorDto> {
}
