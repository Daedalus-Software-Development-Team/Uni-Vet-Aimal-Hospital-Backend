package org.example.service.custom;

import org.example.dto.CustomerDto;
import org.example.entity.CustomerEntity;
import org.example.service.CrudService;

public interface CustomerService extends CrudService<CustomerEntity,CustomerDto> {
}
