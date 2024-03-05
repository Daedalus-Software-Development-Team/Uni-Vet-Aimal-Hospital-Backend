package org.example.controller.custom;

import org.example.controller.CrudController;
import org.example.dto.StaffDto;
import org.example.entity.StaffEntity;

public interface StaffController extends CrudController<StaffEntity, StaffDto> {
}
