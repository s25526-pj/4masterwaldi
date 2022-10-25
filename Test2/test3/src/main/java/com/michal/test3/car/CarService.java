package com.michal.test3.car;

import com.michal.test3.car.model.Car;
import com.michal.test3.common.CarException;
import com.michal.test3.common.ParkingException;
import com.michal.test3.parking.ParkingService;
import com.michal.test3.parking.model.Parking;
import com.michal.test3.ticket.TicketRepository;
import com.michal.test3.ticket.TicketService;
import com.michal.test3.ticket.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final TicketService ticketService;
    private final ParkingService parkingService;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car id=" + id + " not found"));
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void park(int carId, int parkingId) {
        Car car = findById(carId);
        Parking parking = parkingService.findById(parkingId);
        if (car.getParking() != null) {
            throw new CarException("This car is allready parked");
        }
        if (parking.getCars().size() == parking.getCapacity()) {
            throw new ParkingException("Parking is full");
        }
        car.setParking(parking);
        parking.getCars().add(car);
        ticketService.save(car, parking);
    }


    @Transactional
    public void leaveParking(int carId) {
        Car car = findById(carId);
        Parking parking = car.getParking();
        if (car.getParking() == null) {
            throw new ParkingException("This car is not parked anywhere");
        }
        car.setParking(null);
        carRepository.leaveParking(carId);
        parking.getCars().remove(car);
        ticketService.leaveParking(car);
    }
}
