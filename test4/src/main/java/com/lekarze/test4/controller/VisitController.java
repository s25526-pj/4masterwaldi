package com.lekarze.test4.controller;


import com.lekarze.test4.model.dto.VisitDto;
import com.lekarze.test4.model.Visit;
import com.lekarze.test4.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @GetMapping
    public List<VisitDto> findAll() {
        return visitService.findAll().stream()
                .map(VisitDto::fromEntity)
                .toList();
    }

    @PostMapping(value = "schedule")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_DOCTOR")
    public VisitDto save(@RequestBody @Valid VisitDto dto) {
        int patientId = dto.getPatient().getId();
        int doctorId = dto.getDoctor().getId();
        Visit visit = visitService.save(dto.toEntity(), patientId, doctorId);
        return VisitDto.fromEntity(visit);
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {
        visitService.delete(id);
    }

    @GetMapping(value = "/confirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void confirm(@PathVariable("id") int id) {
        visitService.confirmVisit(id);
    }

}
