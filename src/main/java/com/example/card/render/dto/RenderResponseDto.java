package com.example.card.render.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RenderResponseDto {
    private Long id;

    private String fullName;

    private String cardNumber;

    private String recipientName;

    private String recipientCardNumber;

    private Double commission;

    private Double recipientAmount;

    private LocalDateTime practiceDate;
}
