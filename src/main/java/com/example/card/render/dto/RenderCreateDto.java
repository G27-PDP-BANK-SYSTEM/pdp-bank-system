package com.example.card.render.dto;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TimeZoneStorage;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class RenderCreateDto {
    @NotBlank
    private String fullName;

    @NotBlank
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotBlank
    private String recipientName;

    @NotBlank
    @Size(min = 16, max = 16)
    private String recipientCardNumber;

    private Double commission;

    @Size(min = 5000, max = 8000000)
    private Double recipientAmount;


    private LocalDateTime practiceDate = LocalDateTime.now();
}
