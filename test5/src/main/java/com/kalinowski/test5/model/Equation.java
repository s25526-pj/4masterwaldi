package com.kalinowski.test5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Equation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private char operator;
    private LocalDateTime date;


}
