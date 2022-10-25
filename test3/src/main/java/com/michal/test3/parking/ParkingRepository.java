package com.michal.test3.parking;

import com.michal.test3.car.model.Car;
import com.michal.test3.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
}
