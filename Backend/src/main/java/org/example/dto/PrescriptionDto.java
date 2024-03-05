package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrescriptionDto {
    Long prescriptionId;
    Long customerId;
    Long doctorId;
    String description;
    Double total;
    List<PresDetailAndMedCombinedDto> prescriptionDetailArray;
}
