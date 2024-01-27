package org.example.repository;

import org.example.entity.ApointmentEntity;
import org.example.entity.PrescriptionEntity;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<PrescriptionEntity,Long> {
}
