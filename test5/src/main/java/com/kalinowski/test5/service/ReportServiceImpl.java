package com.kalinowski.test5.service;

import com.kalinowski.test5.model.report.Report;
import com.kalinowski.test5.strategy.ReportCreateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {


    private final Map<String, ReportCreateStrategy> strategyMap;

    public Report getReport(String type) {
        ReportCreateStrategy strategy = strategyMap.get(type);
        return strategy.create(type);
    }

}

