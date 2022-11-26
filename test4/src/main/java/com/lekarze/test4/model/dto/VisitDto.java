package com.lekarze.test4.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lekarze.test4.model.Visit;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class VisitDto {

    private int id;

    @NotNull(message = "Doctor can not be null")
    private DoctorDto doctor;

    @NotNull(message = "Patient can not be null")
    private PatientDto patient;

    @Future(message = "Planned visit has to be in future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime date;

    @Min(15)
    @Max(60)
    private int duration;

    public static VisitDto fromEntity(Visit visit) {
        return VisitDto.builder()
                .id(visit.getId())
                .doctor(DoctorDto.fromEntity(visit.getDoctor()))
                .patient(PatientDto.fromEntity(visit.getPatient()))
                .date(visit.getDate())
                .duration(visit.getDuration())
                .build();
    }

    public Visit toEntity() {
        return Visit.builder()
                .date(date)
                .duration(duration)
                .build();
    }

}
