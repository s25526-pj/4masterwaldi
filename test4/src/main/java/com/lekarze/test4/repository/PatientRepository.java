package com.lekarze.test4.repository;

import com.lekarze.test4.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findAllByDeletedIsFalse();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Patient> findWithLockingById(int id);
}
