package com.kalinowski.test5.mapper;

import com.kalinowski.test5.model.Equation;
import com.kalinowski.test5.model.command.CreateEquationCommand;
import org.mapstruct.Mapper;

@Mapper
public interface EquationMapper {

    Equation equationMapFromCreateCommand(CreateEquationCommand source);

}
