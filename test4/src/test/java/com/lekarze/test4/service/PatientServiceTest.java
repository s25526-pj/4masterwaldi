package com.lekarze.test4.service;

import com.lekarze.test4.model.Patient;
import com.lekarze.test4.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void shouldReturnAllPatientsFromRepositoryWhereDeletedIsFalse() {

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
        List<Patient> patientsFromRepo = List.of(patient1, patient2);
        when(patientRepository.findAllByDeletedIsFalse()).thenReturn(patientsFromRepo);

        List<Patient> foundPatients = patientService.findAll();
        assertEquals(patientsFromRepo, foundPatients);

    }


    @Test
    void shouldReturnEntityWhenPatientFoundById() {
        int patientId = 1;
        Patient patientFromRepo = Patient.builder()
                .id(patientId)
                .name("Marcin")
                .surname("Byk")
                .pesel(12312412)
                .build();
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patientFromRepo));
        Patient patientFound = patientService.findById(patientId);

        assertEquals(patientFromRepo, patientFound);

    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenPatientNotFound() {
        int patientId = 1;
        String excMsg = "Patient id=" + patientId + " not found";

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class, () -> patientService.findById(patientId));
        assertEquals(excMsg, exception.getMessage());
    }

    @Test
    void save() {
        Patient patient = Patient.builder()
                .id(1)
                .name("Marek")
                .surname("Markowski")
                .pesel(1236541230)
                .build();
        when(patientRepository.save(patient)).thenReturn(patient);
        Patient savedPatient = patientService.save(patient);

        assertEquals(patient.getName(), savedPatient.getName());
        assertEquals(patient.getSurname(), savedPatient.getSurname());
        assertEquals(patient.getPesel(), savedPatient.getPesel());

    }

}