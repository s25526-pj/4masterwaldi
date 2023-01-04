package com.kalinowski.test5.strategy;

import com.kalinowski.test5.model.command.CreateEquationCommand;
import com.kalinowski.test5.model.Result;

public interface ResultCreateStrategy {

    Result create(CreateEquationCommand command);

}
