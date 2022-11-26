package com.lekarze.test4.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    private boolean deleted = false;
    private boolean confirmed = false;
    private boolean notified = false;
    private LocalDateTime date;
    private int duration;

}
