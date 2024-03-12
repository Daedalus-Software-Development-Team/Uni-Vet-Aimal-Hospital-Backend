package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PetDetailDto {
    Long petDetailId;
    Long petId;
    Long vaccineId;
    String date;
    Boolean given;
    String dosage;
}
