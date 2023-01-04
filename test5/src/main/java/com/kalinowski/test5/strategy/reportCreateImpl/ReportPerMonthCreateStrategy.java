package com.kalinowski.test5.strategy.reportCreateImpl;

import com.kalinowski.test5.model.report.Report;
import com.kalinowski.test5.model.report.ReportPerMonth;
import com.kalinowski.test5.service.EquationServiceImpl;
import com.kalinowski.test5.strategy.ReportCreateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("PER_MONTH")
@RequiredArgsConstructor
public class ReportPerMonthCreateStrategy implements ReportCreateStrategy {

    private final EquationServiceImpl equationServiceImpl;

    @Override
    public Report create(String type) {
        return ReportPerMonth.builder()
                .type(type)
                .january(equationServiceImpl.findAllByMonth(1).size())
                .february(equationServiceImpl.findAllByMonth(2).size())
                .march(equationServiceImpl.findAllByMonth(3).size())
                .april(equationServiceImpl.findAllByMonth(4).size())
                .may(equationServiceImpl.findAllByMonth(5).size())
                .june(equationServiceImpl.findAllByMonth(6).size())
                .july(equationServiceImpl.findAllByMonth(7).size())
                .august(equationServiceImpl.findAllByMonth(8).size())
                .september(equationServiceImpl.findAllByMonth(9).size())
                .october(equationServiceImpl.findAllByMonth(10).size())
                .november(equationServiceImpl.findAllByMonth(11).size())
                .december(equationServiceImpl.findAllByMonth(12).size())
                .build();
    }
}
