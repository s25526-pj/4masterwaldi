package com.lekarze.test4.service;

import com.lekarze.test4.model.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DoctorService {


    List<Doctor> findAll();

    Doctor findById(int id);

    Doctor findWithLockingById(int id);

    Doctor save(Doctor doctor);

    void delete(int id);

}
