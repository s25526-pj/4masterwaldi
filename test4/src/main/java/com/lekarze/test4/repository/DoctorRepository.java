package com.lekarze.test4.repository;

import com.lekarze.test4.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor> findAllByDeletedIsFalse();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    Optional<Doctor> findWithLockingById(int id);
}
