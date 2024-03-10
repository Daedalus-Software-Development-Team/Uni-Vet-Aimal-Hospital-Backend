package org.example.repository;

import org.example.entity.PrescriptionDetailEntity;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionDetailRepository extends CrudRepository<PrescriptionDetailEntity,Long> {
}
