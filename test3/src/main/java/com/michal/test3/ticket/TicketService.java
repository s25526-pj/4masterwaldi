package com.michal.test3.ticket;

import com.michal.test3.car.model.Car;
import com.michal.test3.parking.model.Parking;
import com.michal.test3.ticket.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket save(Car car, Parking parking) {
        Ticket ticket = Ticket.builder()
                .car(car)
                .parking(parking)
                .arrival(LocalDateTime.now())
                .build();
        car.getTickets().add(ticket);
        parking.getTickets().add(ticket);
        return ticketRepository.save(ticket);
    }
    @Transactional
    public void leaveParking(Car car) {
        Ticket ticket = ticketRepository.findByCarIdAndLeavingIsNull(car);
        ticket.setLeaving(LocalDateTime.now());
        ticketRepository.leaveParking(car, LocalDateTime.now());
    }

}
