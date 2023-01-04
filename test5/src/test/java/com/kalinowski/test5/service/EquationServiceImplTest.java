package com.kalinowski.test5.service;

import com.kalinowski.test5.model.Equation;
import com.kalinowski.test5.repository.EquationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquationServiceImplTest {

    @Mock
    private EquationRepository equationRepository;

    @InjectMocks
    private EquationServiceImpl equationService;

    @Test
    void shouldSaveEquationOperatorAndDateToRepository() {

        Equation equation = Equation.builder()
                .id(1)
                .operator('-')
                .date(LocalDateTime.now())
                .build();

        when(equationRepository.save(equation)).thenReturn(equation);
        Equation savedEquation = equationService.save(equation);

        assertEquals(equation, savedEquation);

    }

    @Test
    void shouldReturnAllEquationsByOperator() {

        Equation equation1 = Equation.builder()
                .id(1)
                .operator('-')
                .date(LocalDateTime.now())
                .build();
        Equation equation2 = Equation.builder()
                .id(2)
                .operator('-')
                .date(LocalDateTime.now())
                .build();

        List<Equation> equationsFromRepo = List.of(equation1, equation2);
        when(equationRepository.findAllByOperator('/')).thenReturn(equationsFromRepo);
        List<Equation> foundEquations = equationService.findAllByOperator('/');

        assertEquals(equationsFromRepo, foundEquations);

    }

    @Test
    void shouldReturnAllEquationsByMonth() {

        Equation equation1 = Equation.builder()
                .id(1)
                .operator('+')
                .date(LocalDateTime.now())
                .build();
        Equation equation2 = Equation.builder()
                .id(2)
                .operator('*')
                .date(LocalDateTime.now())
                .build();

        List<Equation> equationsFromRepo = List.of(equation1, equation2);
        when(equationRepository.findAllByMonth(1)).thenReturn(equationsFromRepo);
        List<Equation> foundEquations = equationService.findAllByMonth(1);

        assertEquals(equationsFromRepo, foundEquations);

    }

    @Test
    void shouldReturnAllEquationsByDayOfWeek() {

        Equation equation1 = Equation.builder()
                .id(1)
                .operator('+')
                .date(LocalDateTime.now())
                .build();
        Equation equation2 = Equation.builder()
                .id(2)
                .operator('*')
                .date(LocalDateTime.now())
                .build();

        List<Equation> equationsFromRepo = List.of(equation1, equation2);
        when(equationRepository.findAllByDayOfWeek(LocalDateTime.now().getDayOfWeek().getValue())).thenReturn(equationsFromRepo);
        List<Equation> foundEquations = equationService.findAllByDayOfWeek(LocalDateTime.now().getDayOfWeek().getValue());

        assertEquals(equationsFromRepo, foundEquations);

    }

}