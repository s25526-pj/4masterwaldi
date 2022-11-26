package com.lekarze.test4.controller;

import com.lekarze.test4.model.Patient;
import com.lekarze.test4.model.dto.PatientDto;
import com.lekarze.test4.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> findAll() {
        return patientService.findAll().stream()
                .map(PatientDto::fromEntity)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto save(@RequestBody @Valid PatientDto dto) {
        Patient patient = patientService.save(dto.toEntity());
        return PatientDto.fromEntity(patient);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {
        patientService.delete(id);
    }

}
