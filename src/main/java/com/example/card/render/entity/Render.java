package com.example.card.render.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "render")
public class Render {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String fullName;

    private String cardNumber;

    private String recipientName;

    private String recipientCardNumber;

    private Double commission;

    private Double recipientAmount;

    private LocalDateTime practiceDate;


}
