package com.kalinowski.test5.service;

import com.kalinowski.test5.model.Result;
import com.kalinowski.test5.model.command.CreateEquationCommand;
import com.kalinowski.test5.strategy.ResultCreateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final Map<String, ResultCreateStrategy> strategyMap;

    public Result getEquationResult(CreateEquationCommand command) {
        String operator = String.valueOf(command.getOperator());
        ResultCreateStrategy strategy = strategyMap.get(operator);
        return strategy.create(command);
    }

}

