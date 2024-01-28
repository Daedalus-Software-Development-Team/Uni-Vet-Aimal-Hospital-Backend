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
@SequenceGenerator(name = "prescriptionDetail_sequence", sequenceName = "prescriptionDetail_sequence", allocationSize = 1 )
public class PrescriptionDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescriptionDetail_sequence")
    Long prescriptionDetailId;
}
