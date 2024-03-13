package org.example.repository;

import org.example.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity,Long> {
}
