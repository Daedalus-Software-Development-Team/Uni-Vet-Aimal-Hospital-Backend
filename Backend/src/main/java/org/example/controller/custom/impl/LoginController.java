package org.example.controller.custom.impl;

import org.example.dto.DoctorDto;
import org.example.dto.LoginRequestDto;
import org.example.dto.Response;
import org.example.dto.StaffDto;
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

    @PostMapping("/login")
    public Object confirmLogin(@RequestBody LoginRequestDto dto){
        List<DoctorDto> allDoctors = doctorService.getAll();
        List<StaffDto> allStaff = staffService.getAll();

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
        return new Response(String.format("Invalid Username or Password"));
    }
}
