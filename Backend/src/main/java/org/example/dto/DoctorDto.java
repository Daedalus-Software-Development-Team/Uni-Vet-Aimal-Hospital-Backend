package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorDto {
    Long doctorId;
    String name;
    Double salary;
    String description;
    Double channelingFee;
    String email;
    String password;
}
