package com.kalinowski.test5.service;

import com.kalinowski.test5.model.Equation;


import java.util.List;

public interface EquationService {

    Equation save(Equation equation);

    List<Equation> findAllByOperator(char operator);

    List<Equation> findAllByMonth(int month);

    List<Equation> findAllByDayOfWeek(int dayOfWeek);

}
