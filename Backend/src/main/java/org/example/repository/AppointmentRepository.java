package org.example.repository;

import org.example.entity.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<AppointmentEntity,Long> {
}
