package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto {
    Long staffId;
    String name;
    Double salary;
    String description;
    String email;
    String password;
    String status;
    String position;
}
