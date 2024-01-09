package org.example.pdpbanksystem.card.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Card number cannot be blank")
    @Column(unique = true,nullable = false)
    private String cardNumber;

    @NotNull(message = "Expiration date cannot be null")
    private LocalDate expirationDate;

    @NotBlank(message = "CVV cannot be blank")
    @Pattern(regexp = "\\d{3}", message = "CVV must be a 3-digit number")
    private String cvv;

    @NotBlank(message = "Owner name cannot be blank")
    private String ownerName;

    @NotNull(message = "Balance cannot be null")
    @Positive(message = "Balance must be a positive number")
    private Double balance;


}
