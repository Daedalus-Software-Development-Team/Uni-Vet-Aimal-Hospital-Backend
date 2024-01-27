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
@SequenceGenerator(name = "doctor_sequence", sequenceName = "doctor_sequence", allocationSize = 1 )
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_sequence")
    Long id;
    private String doctorId;
}
