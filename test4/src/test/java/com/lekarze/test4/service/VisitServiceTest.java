package com.lekarze.test4.service;

import com.lekarze.test4.model.Doctor;
import com.lekarze.test4.model.Patient;
import com.lekarze.test4.model.Visit;
import com.lekarze.test4.repository.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitServiceTest {

    @Mock
    private VisitRepository visitRepository;
    @InjectMocks
    private VisitService visitService;

    @Test
    void shouldReturnAllVisitsFromRepoWhereDeletedIsFalse() {
        Patient patient1 = Patient.builder()
                .id(1)
                .name("Marek")
                .surname("Mostowiak")
                .pesel(21312412)
                .build();

        Patient patient2 = Patient.builder()
                .id(1)
                .name("Marcin")
                .surname("Mostek")
                .pesel(3123123)
                .build();

        Doctor doctor1 = Doctor.builder()
                .id(1)
                .name("Marek")
                .surname("Mostowiak")
                .nip(21312412)
                .build();

        Doctor doctor2 = Doctor.builder()
                .id(1)
                .name("Marcin")
                .surname("Mostek")
                .nip(3123123)
                .build();

        Visit visit1 = Visit.builder()
                .doctor(doctor1)
                .patient(patient1)
                .date(LocalDateTime.now())
                .duration(30)
                .build();

        Visit visit2 = Visit.builder()
                .doctor(doctor2)
                .patient(patient2)
                .date(LocalDateTime.now())
                .duration(15)
                .build();

        List<Visit> visitsFromRepo = List.of(visit1, visit2);
        when(visitRepository.findAllByDeletedIsFalse()).thenReturn(visitsFromRepo);
        List<Visit> visitsFound = visitService.findAll();
        assertEquals(visitsFromRepo, visitsFound);
    }

    @Test
    void shouldReturnEntityWhenVisitFoundById() {
        int visitId = 1;
        Patient patient = Patient.builder()
                .id(1)
                .name("Marcin")
                .surname("Mostek")
                .pesel(3123123)
                .build();

        Doctor doctor = Doctor.builder()
                .id(1)
                .name("Marek")
                .surname("Mostowiak")
                .nip(21312412)
                .build();

        Visit visit = Visit.builder()
                .id(visitId)
                .doctor(doctor)
                .patient(patient)
                .date(LocalDateTime.now())
                .duration(30)
                .build();

        when(visitRepository.findById(visitId)).thenReturn(Optional.of(visit));
        Visit visitFound = visitService.findById(visitId);
        assertEquals(visit, visitFound);
    }

    @Test
    void shouldSaveVisitToRepo() {
        int doctorId = 1;
        int patientId = 1;
        Patient patient = Patient.builder()
                .id(patientId)
                .name("Marcin")
                .surname("Mostek")
                .pesel(3123123)
                .build();

        Doctor doctor = Doctor.builder()
                .id(doctorId)
                .name("Marek")
                .surname("Mostowiak")
                .nip(21312412)
                .build();

        Visit visit = Visit.builder()
                .id(1)
                .doctor(doctor)
                .patient(patient)
                .date(LocalDateTime.now())
                .duration(30)
                .build();

        when(visitRepository.save(visit)).thenReturn(visit);
        Visit savedVisit = visitService.save(visit, patientId, doctorId);
        assertEquals(visit, savedVisit);
    }

}