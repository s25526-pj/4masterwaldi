package com.michal.test3.ticket;

import com.michal.test3.car.model.Car;
import com.michal.test3.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllByCarId(int id);

    List<Ticket> findAllByParkingId(int id);

    @Query("SELECT t FROM Ticket t WHERE t.leaving  IS NULL AND t.car = :car")
    Ticket findByCarIdAndLeavingIsNull(@Param("car") Car car);

    @Transactional
    @Modifying
    @Query("update Ticket t set t.leaving = :leavingDate where t.car = :car")
    void leaveParking(@Param("car") Car car, @Param("leavingDate") LocalDateTime leavingDate);
}
