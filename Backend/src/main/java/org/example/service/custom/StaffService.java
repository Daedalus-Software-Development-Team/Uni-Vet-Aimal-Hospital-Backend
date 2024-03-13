package org.example.service.custom;

import org.example.dto.StaffDto;
import org.example.entity.StaffEntity;
import org.example.service.CrudService;
import org.springframework.data.repository.CrudRepository;

public interface StaffService extends CrudService<StaffEntity, StaffDto> {
}
