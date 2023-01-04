package com.kalinowski.test5.repository;

import com.kalinowski.test5.model.Equation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquationRepository extends JpaRepository<Equation, Integer> {

    List<Equation> findAllByOperator(char operator);

    @Query("SELECT e FROM Equation e WHERE month(e.date) = :month")
    List<Equation> findAllByMonth(@Param("month") int month);

    @Query("SELECT e FROM Equation e WHERE DAYOFWEEK(e.date) = :dayOfWeek")
    List<Equation> findAllByDayOfWeek(@Param("dayOfWeek") int dayOfWeek);

}
