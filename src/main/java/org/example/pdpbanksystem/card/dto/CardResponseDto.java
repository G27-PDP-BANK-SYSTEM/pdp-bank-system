package org.example.pdpbanksystem.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardResponseDto {
    private Integer id;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;
    private String ownerName;
    private Double balance;
}
