package org.example.controller.custom;


import org.example.controller.CrudController;
import org.example.dto.CustomerDto;
import org.example.dto.Response;
import org.example.entity.CustomerEntity;

public interface CustomerController extends CrudController<CustomerEntity, CustomerDto> {
}
