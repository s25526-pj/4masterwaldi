package com.michal.test3.car;

import com.michal.test3.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Transactional
    @Modifying
    @Query("update Car c set c.parking = NULL where c.id = :carId")
    void leaveParking(@Param("carId") int carId);
}
