package com.lekarze.test4.service;

import com.lekarze.test4.model.Doctor;
import com.lekarze.test4.model.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final String SUBJECT = "Visit confirmation";
    private static final String SENDER_EMAIL = "bringbackv8@gmail.com";

    private static final String BASE_URL = "http://localhost:8080/api/v1/visits/";

    private final JavaMailSender mailSender;

    public void sendEmail(Visit visit) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER_EMAIL);
        message.setSubject(SUBJECT);
        message.setTo(visit.getPatient().getEmail());
        Doctor doctor = visit.getDoctor();
        message.setText("You have an appointment to " + doctor.getName() + " " + doctor.getSurname() + " on " + visit.getDate() + ".\n"
                + "Click this link to cancel appointment: " + BASE_URL + "delete/" + visit.getId() +
                "\nPlease click this link to confirm your appointment: " + BASE_URL + "confirm/" + visit.getId());
        mailSender.send(message);
        System.out.println("Mail sent successfully!");
    }

}
