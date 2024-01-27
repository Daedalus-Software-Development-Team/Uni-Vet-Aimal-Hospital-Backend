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
@SequenceGenerator(name = "prescription_sequence", sequenceName = "prescription_sequence", allocationSize = 1 )
public class PrescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescription_sequence")
    Long prescriptionId;
}
