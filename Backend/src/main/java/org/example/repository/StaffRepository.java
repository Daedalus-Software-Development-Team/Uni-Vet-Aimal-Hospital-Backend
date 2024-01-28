package org.example.repository;

import org.example.entity.ApointmentEntity;
import org.example.entity.StaffEntity;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<StaffEntity,Long> {
}
