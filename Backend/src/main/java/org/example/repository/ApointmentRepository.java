package org.example.repository;

import org.example.entity.ApointmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface ApointmentRepository  extends CrudRepository<ApointmentEntity,Long> {
}
