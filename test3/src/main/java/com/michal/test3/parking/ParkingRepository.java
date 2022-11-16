package com.michal.test3.parking;

import com.michal.test3.car.model.Car;
import com.michal.test3.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Parking> findWithLockingById(int id);

}
