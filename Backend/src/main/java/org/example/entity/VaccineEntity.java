package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class VaccineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long vaccineId;
    String vaccineName;
    Double price;
    Boolean isAvailable;
    String category;
    String type;
}
