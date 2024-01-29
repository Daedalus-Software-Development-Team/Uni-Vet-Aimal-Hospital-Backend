package org.example.repository;

import org.example.entity.ApointmentEntity;
import org.example.entity.DoctorEntity;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<DoctorEntity,Long> {
}
