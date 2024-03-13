package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VaccineDto {
    Long vaccineId;
    String vaccineName;
    Double price;
    Boolean isAvailable;
    String category;
    String type;
}
