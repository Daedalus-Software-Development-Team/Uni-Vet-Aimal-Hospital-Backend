package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity

public class PrescriptionDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long prescriptionDetailId;
    Long prescriptionId;
    Long medicineId;
    Boolean available;
    Boolean beforeMeal;
    Double quantity;
    Integer days;
    Double price;
}
