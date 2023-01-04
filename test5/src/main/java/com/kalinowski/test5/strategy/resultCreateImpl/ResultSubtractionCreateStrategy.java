package com.kalinowski.test5.strategy.resultCreateImpl;

import com.kalinowski.test5.model.command.CreateEquationCommand;
import com.kalinowski.test5.model.Result;
import com.kalinowski.test5.strategy.ResultCreateStrategy;
import org.springframework.stereotype.Component;

@Component("-")
public class ResultSubtractionCreateStrategy implements ResultCreateStrategy {

    @Override
    public Result create(CreateEquationCommand command) {
        double result = command.getNum1() - command.getNum2();
        return Result.builder()
                .result(result)
                .build();
    }

}
