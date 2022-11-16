package com.michal.test3.parking.model;

import com.michal.test3.car.model.Car;
import com.michal.test3.ticket.model.Ticket;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;
    private int capacity;

    @OneToMany(mappedBy = "parking")
    private Set<Car> cars;

    @OneToMany(mappedBy = "parking")
    private Set<Ticket> tickets;

    public int getNumberOfFreePlaces() {
        return capacity - cars.size();
    }

    @Override
    public String toString() {
        return name + " " + city;
    }


}
