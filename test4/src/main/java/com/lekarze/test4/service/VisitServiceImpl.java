package com.lekarze.test4.service;

import com.lekarze.test4.exception.DoctorUnavailableException;
import com.lekarze.test4.exception.PatientUnavailableException;
import com.lekarze.test4.model.Doctor;
import com.lekarze.test4.model.Patient;
import com.lekarze.test4.model.Visit;
import com.lekarze.test4.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAllByDeletedIsFalse();
    }
    @Override
    public Visit findById(int id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visit id=" + id + " not found"));
    }
    @Override
    @Transactional
    public Visit save(Visit visit, int patientId, int doctorId) {
        List<Visit> patientVisits = visitRepository.findAllByPatientIdAndDateBetweenAndDeletedIsFalse(patientId,
                visit.getDate(), visit.getDate().plusMinutes(visit.getDuration()));

        List<Visit> doctorVisits = visitRepository.findAllByDoctorIdAndDateBetweenAndDeletedIsFalse(doctorId,
                visit.getDate(), visit.getDate().plusMinutes(visit.getDuration()));

        if (!patientVisits.isEmpty()) {
            throw new PatientUnavailableException("Patient have scheduled visit at this time");
        }
        if (!doctorVisits.isEmpty()) {
            throw new DoctorUnavailableException("Doctor have scheduled visit at this time");
        }
        Patient patient = patientService.findWithLockingById(patientId);
        Doctor doctor = doctorService.findWithLockingById(doctorId);
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        return visitRepository.save(visit);
    }
    @Override
    public void delete(int id) {
        visitRepository.deleteById(id);
    }

    @Override
    public List<Visit> findAllNotNotifiedInNextTwentyFourHours() {
        return visitRepository
                .findAllByNotifiedIsFalseAndDateBetween(LocalDateTime.now().plusHours(23).plusMinutes(59),
                        LocalDateTime.now().plusHours(24).plusMinutes(1));
    }

    @Override
    @Transactional
    public void confirmSendingNotification(int id) {
        visitRepository.setNotifiedToTrue(id);
    }

    @Override
    @Transactional
    public void confirmVisit(int id) {
        visitRepository.confirmVisit(id);
    }
}
