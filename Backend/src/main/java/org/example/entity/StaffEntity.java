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
@SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence", allocationSize = 1 )
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_sequence")
    Long staffId;
}
