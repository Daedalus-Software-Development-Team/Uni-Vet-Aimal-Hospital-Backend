package org.example.controller.custom.impl;



import org.example.controller.custom.CustomerController;
import org.example.dto.CustomerDto;
import org.example.dto.EmailDto;
import org.example.dto.Response;
import org.example.entity.CustomerEntity;
import org.example.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerControllerImpl implements CustomerController{
    @Autowired
    CustomerService service;

    @PostMapping("/customer")
    @Override
    public CustomerEntity saveOrUpdate(@RequestBody CustomerDto dto)  {
        return service.save(dto);
    }
    @GetMapping("/customer")
    @Override
    public List<CustomerDto> getAll() throws InterruptedException {
        return service.getAll();
    }

    @Override
    @DeleteMapping("/customer/{customerId}")
    public org.example.dto.Response delete(@PathVariable Long customerId)  {
        boolean isRemoved = service.delete(customerId);
        if(isRemoved) {
            return new Response(String.format("Removed Customer Id (%s)", customerId));

        } else {
            return new Response(String.format("Customer Id (%s) Invalid", customerId));

        }
    }

    @GetMapping("/customerNextId")
    @Override
    public Long getNextId() throws Exception {
        return service.getNextId();
    }
    @GetMapping("/customer/{customerId}")
    @Override
    public CustomerDto getById(@PathVariable Long customerId) {
        return service.getById(customerId);
    }

}
