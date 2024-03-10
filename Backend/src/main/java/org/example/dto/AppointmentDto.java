package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDto {
    Long appointmentId;
    String name;
    String email;
    String contactNumber;
    String petCategory;
    String date;
    String time;
    String comment;
}
