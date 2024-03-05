package org.example.repository;

import org.example.entity.MedicineEntity;
import org.example.entity.OtherExpenseEntity;
import org.springframework.data.repository.CrudRepository;

public interface MedicineRepository extends CrudRepository<MedicineEntity,Long> {
}
