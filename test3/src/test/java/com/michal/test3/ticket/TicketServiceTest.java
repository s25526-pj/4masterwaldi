package com.michal.test3.ticket;

import com.michal.test3.car.CarService;
import com.michal.test3.car.model.Car;
import com.michal.test3.parking.ParkingService;
import com.michal.test3.parking.model.Parking;
import com.michal.test3.ticket.model.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;
    @InjectMocks
    private TicketService ticketService;

    @Test
    void save() {

        Parking parking = Parking.builder()
                .id(1)
                .name("Parking")
                .city("Warszawa")
                .capacity(4)
                .cars(new ArrayList<>())
                .tickets(new ArrayList<>())
                .build();

        Car car = Car.builder()
                .id(1)
                .brand("BMW")
                .model("3")
                .tickets(new ArrayList<>())
                .build();

        car.setParking(parking);
        parking.getCars().add(car);
        Ticket ticket = Ticket.builder()
                .id(1)
                .parking(parking)
                .car(car)
                .arrival(LocalDateTime.now())
                .build();

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket savedTicket = ticketService.save(car, parking);


        assertEquals(ticket.getId(), savedTicket.getId());
    }


}