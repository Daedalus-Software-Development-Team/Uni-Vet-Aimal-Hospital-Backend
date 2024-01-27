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
@SequenceGenerator(name = "otherExpense_sequence", sequenceName = "otherExpense_sequence", allocationSize = 1 )
public class OtherExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otherExpense_sequence")
    Long id;
    private String otherExpenseId;
}
