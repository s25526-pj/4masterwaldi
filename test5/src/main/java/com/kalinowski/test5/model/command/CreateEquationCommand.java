package com.kalinowski.test5.model.command;

import com.kalinowski.test5.validation.ValidOperator;
import lombok.Data;

@Data
public class CreateEquationCommand {

    private double num1;
    private double num2;
    @ValidOperator
    private char operator;

    public double getResult() {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        throw new IllegalArgumentException("Bad opperand");
    }

}
