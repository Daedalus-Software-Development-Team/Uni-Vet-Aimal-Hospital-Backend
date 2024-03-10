package org.example.controller.custom;

import org.example.controller.CrudController;
import org.example.dto.AppointmentDto;
import org.example.entity.AppointmentEntity;

public interface AppointmentController extends CrudController<AppointmentEntity, AppointmentDto> {
}
