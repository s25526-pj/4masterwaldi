package com.lekarze.test4.model.dto;


import com.lekarze.test4.model.Patient;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class PatientDto {

    private int id;
    @NotNull(message = "Name can not be null")
    @Pattern(regexp = "[A-Z][a-z]+", message = "name in wrong format")
    private String name;
    @NotNull(message = "Surname can not be null")
    @Pattern(regexp = "[A-Z][a-z]+", message = "surname in wrong format")
    private String surname;
    @NotNull(message = "Pesel can not be null")
    private long pesel;
    @NotNull(message = "Email can not be null")
    @Pattern(regexp = "[a-z0-9]+?.[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Email in wrong format")
    private String email;

    public static PatientDto fromEntity(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .pesel(patient.getPesel())
                .email(patient.getEmail())
                .build();
    }

    public Patient toEntity() {
        return Patient.builder()
                .name(name)
                .surname(surname)
                .pesel(pesel)
                .email(email)
                .build();
    }

}
