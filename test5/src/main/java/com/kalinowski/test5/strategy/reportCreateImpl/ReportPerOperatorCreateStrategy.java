package com.kalinowski.test5.strategy.reportCreateImpl;

import com.kalinowski.test5.model.report.Report;
import com.kalinowski.test5.model.report.ReportPerOperator;
import com.kalinowski.test5.service.EquationServiceImpl;
import com.kalinowski.test5.strategy.ReportCreateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("PER_OPERATOR")
@RequiredArgsConstructor
public class ReportPerOperatorCreateStrategy implements ReportCreateStrategy {

    private final EquationServiceImpl equationServiceImpl;

    @Override
    public Report create(String type) {
        return ReportPerOperator.builder()
                .type(type)
                .plus(equationServiceImpl.findAllByOperator('+').size())
                .minus(equationServiceImpl.findAllByOperator('-').size())
                .divide(equationServiceImpl.findAllByOperator('/').size())
                .multiply(equationServiceImpl.findAllByOperator('*').size())
                .build();
    }

}
