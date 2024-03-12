package org.example.controller.custom.impl;

import org.example.dto.*;
import org.example.service.custom.CustomerService;
import org.example.service.custom.DoctorService;
import org.example.service.custom.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    StaffService staffService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/login")
    public Object confirmLogin(@RequestBody LoginRequestDto dto){
        List<DoctorDto> allDoctors = doctorService.getAll();
        List<StaffDto> allStaff = staffService.getAll();
        List<CustomerDto> allCustomer = customerService.getAll();

        for (CustomerDto customer:allCustomer) {
            if (customer.getPassword() !=null && customer.getEmail() !=null  &&customer.getEmail().equalsIgnoreCase(dto.getEmail()) && customer.getPassword().equalsIgnoreCase(dto.getPassword())){
                return customer;
            }
        }
        for (DoctorDto doctor:allDoctors) {
            if (doctor.getPassword() !=null && doctor.getEmail() !=null  && doctor.getEmail().equalsIgnoreCase(dto.getEmail()) && doctor.getPassword().equalsIgnoreCase(dto.getPassword())){
                return doctor;
            }
        }
        for (StaffDto staff:allStaff) {
            if (staff.getPassword() !=null && staff.getEmail() !=null  &&staff.getEmail().equalsIgnoreCase(dto.getEmail()) && staff.getPassword().equalsIgnoreCase(dto.getPassword())){
                return staff;
            }
        }
        return new Response("Invalid Username or Password");
    }
}
