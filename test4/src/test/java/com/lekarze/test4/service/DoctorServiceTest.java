package com.lekarze.test4.service;

import com.lekarze.test4.model.Doctor;
import com.lekarze.test4.repository.DoctorRepository;
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
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @Test
    void shouldReturnAllDoctorsFromRepositoryWhereDeletedIsFalse() {

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
        List<Doctor> doctorsFromRepo = List.of(doctor1, doctor2);
        when(doctorRepository.findAllByDeletedIsFalse()).thenReturn(doctorsFromRepo);

        List<Doctor> foundDoctors = doctorService.findAll();
        assertEquals(doctorsFromRepo, foundDoctors);

    }


    @Test
    void shouldReturnEntityWhenDoctorFoundById() {
        int doctorId = 1;
        Doctor doctorFromRepo = Doctor.builder()
                .id(doctorId)
                .name("Marcin")
                .surname("Byk")
                .nip(12312412)
                .build();
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctorFromRepo));
        Doctor doctorFound = doctorService.findById(doctorId);

        assertEquals(doctorFromRepo, doctorFound);

    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenPatientNotFound() {
        int doctorId = 1;
        String excMsg = "Doctor id=" + doctorId + " not found";

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class, () -> doctorService.findById(doctorId));
        assertEquals(excMsg, exception.getMessage());
    }

    @Test
    void save() {
        Doctor doctor = Doctor.builder()
                .id(1)
                .name("Marek")
                .surname("Markowski")
                .nip(1236541230)
                .build();
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        Doctor savedDoctor = doctorService.save(doctor);

        assertEquals(doctor.getName(), savedDoctor.getName());
        assertEquals(doctor.getSurname(), savedDoctor.getSurname());
        assertEquals(doctor.getNip(), savedDoctor.getNip());

    }

}