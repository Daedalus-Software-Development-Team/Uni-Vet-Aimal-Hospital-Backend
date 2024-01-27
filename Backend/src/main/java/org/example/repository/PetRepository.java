package org.example.repository;

import org.example.entity.ApointmentEntity;
import org.example.entity.PetEntity;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<PetEntity,Long> {
}
