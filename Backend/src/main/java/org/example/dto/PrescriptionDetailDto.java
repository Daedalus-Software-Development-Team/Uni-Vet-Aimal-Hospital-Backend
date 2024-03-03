package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrescriptionDetailDto {
    Long prescriptionDetailId;
    Long prescriptionId;
    Long medicineId;
    Boolean available;
    Boolean beforeMeal;
    Double quantity;
    Integer days;
    Double price;
}
