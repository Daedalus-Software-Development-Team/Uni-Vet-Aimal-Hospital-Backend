package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.dto.PrescriptionDetailDto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class PrescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long prescriptionId;
    Long customerId;
    Long doctorId;
    Long petId;
    String description;
    Double total;
//    List<PrescriptionDetailDto> prescriptionDetailList;
}
