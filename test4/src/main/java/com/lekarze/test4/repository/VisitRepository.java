package com.lekarze.test4.repository;

import com.lekarze.test4.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {

    List<Visit> findAllByDeletedIsFalse();

    List<Visit> findAllByDoctorIdAndDateBetweenAndDeletedIsFalse(int id, LocalDateTime timeFrom, LocalDateTime timeTo);

    List<Visit> findAllByPatientIdAndDateBetweenAndDeletedIsFalse(int id, LocalDateTime timeFrom, LocalDateTime timeTo);

    List<Visit> findAllByNotifiedIsFalseAndDateBetween(LocalDateTime timeFrom, LocalDateTime timeTo);

    @Transactional
    @Modifying
    @Query("update Visit visit set visit.notified = TRUE where visit.id = :visitId")
    void setNotifiedToTrue(@Param("visitId") int visitId);

    @Transactional
    @Modifying
    @Query("update Visit visit set visit.confirmed = TRUE where visit.id = :visitId")
    void confirmVisit(@Param("visitId") int visitId);

}
