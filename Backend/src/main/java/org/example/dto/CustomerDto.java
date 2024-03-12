package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    Long customerId;
    String nic;
    String firstName;
    String lastName;
    String contact;
    String email;

}
