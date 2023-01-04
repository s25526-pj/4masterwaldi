package com.kalinowski.test5.controller;

import com.kalinowski.test5.mapper.EquationMapper;
import com.kalinowski.test5.model.Result;
import com.kalinowski.test5.model.command.CreateEquationCommand;
import com.kalinowski.test5.model.report.Report;
import com.kalinowski.test5.service.EquationService;
import com.kalinowski.test5.service.ReportService;
import com.kalinowski.test5.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equation")
public class EquationController {

    private final EquationService equationServiceImpl;

    private final ReportService reportService;
    private final EquationMapper equationMapper;
    private final ResultService resultService;

    @PostMapping
    public Result getEquationResult(@RequestBody @Valid CreateEquationCommand command) {
        equationServiceImpl.save(equationMapper.equationMapFromCreateCommand(command));
        return resultService.getEquationResult(command);
    }

    @GetMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public Report getReport(@PathVariable String type) {
        return reportService.getReport(type);
    }


}
