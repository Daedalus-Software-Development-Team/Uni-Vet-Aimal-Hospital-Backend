package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PresDetailAndMedCombinedDto {
    Long prescriptionDetailId;
    Boolean available;
    Boolean beforeMeal;
    String dailyQuantity;
    String days;
    String dosage;
    Long medicineId;
    String medicineName;
    String price;
}
