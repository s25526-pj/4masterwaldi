package com.kalinowski.test5.service;

import com.kalinowski.test5.model.Result;
import com.kalinowski.test5.model.command.CreateEquationCommand;

public interface ResultService {


    Result getEquationResult(CreateEquationCommand command);

}
