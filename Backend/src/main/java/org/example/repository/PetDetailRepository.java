package org.example.repository;

import org.example.entity.ApointmentEntity;
import org.example.entity.PetDetailEntity;
import org.springframework.data.repository.CrudRepository;

public interface PetDetailRepository extends CrudRepository<PetDetailEntity,Long> {
}
