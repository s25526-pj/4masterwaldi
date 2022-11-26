package com.lekarze.test4.model.dto;


import com.lekarze.test4.model.Doctor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Data
public class DoctorDto {

    private int id;
    @NotNull(message = "Name can not be null")
    @Pattern(regexp = "[A-Z][a-z]+", message = "name in wrong format")
    private String name;
    @NotNull(message = "Surname can not be null")
    @Pattern(regexp = "[A-Z][a-z]+", message = "surname in wrong format")
    private String surname;
    @NotNull(message = "Nip can not be null")
    private long nip;

    public static DoctorDto fromEntity(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .nip(doctor.getNip())
                .build();
    }

    public Doctor toEntity() {
        return Doctor.builder()
                .name(name)
                .surname(surname)
                .nip(nip)
                .build();
    }

}
