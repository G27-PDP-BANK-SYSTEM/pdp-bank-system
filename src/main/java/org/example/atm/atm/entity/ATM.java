package org.example.atm.atm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ATM
{
    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private UUID id;

    @Column(name = "atm_model")
    private String model;

    @Column(name = "atm_address")
    private String address;

    @Column(name = "atm_longitude")
    private Double longitude;

    @Column(name = "atm_latitude")
    private Double latitude;
}
