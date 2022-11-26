package com.lekarze.test4.service;

import com.lekarze.test4.model.Patient;

import java.util.List;


public interface PatientService {


    List<Patient> findAll();

    Patient findById(int id);

    Patient findWithLockingById(int id);

    Patient save(Patient patient);

    void delete(int id);

}
