package com.lekarze.test4.service;

import com.lekarze.test4.model.Visit;

import java.util.List;


public interface VisitService {

    List<Visit> findAll();

    Visit findById(int id);

    Visit save(Visit visit, int patientId, int doctorId);

    void delete(int id);

    List<Visit> findAllNotNotifiedInNextTwentyFourHours();

    void confirmSendingNotification(int id);

    void confirmVisit(int id);

}
