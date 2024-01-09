package org.example.atm.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ATMCreateDto {

    private String model;
    private String address;
    private Double longitude;
    private Double latitude;
}
