package com.michal.test3.car.model;

//import com.michal.test3.parking.model.Parking;
import com.michal.test3.common.ParkingException;
import com.michal.test3.parking.model.Parking;
import com.michal.test3.ticket.model.Ticket;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;

    @ManyToOne
    private Parking parking;

    @OneToMany(mappedBy = "car")
    private List<Ticket> tickets;

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public String toString() {
        return brand + " " + model;
    }


}