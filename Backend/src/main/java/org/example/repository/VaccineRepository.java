package org.example.repository;

import org.example.entity.VaccineEntity;
import org.springframework.data.repository.CrudRepository;

public interface VaccineRepository extends CrudRepository<VaccineEntity,Long> {

}
