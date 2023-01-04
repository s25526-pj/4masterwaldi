package com.kalinowski.test5.strategy;

import com.kalinowski.test5.model.report.Report;

public interface ReportCreateStrategy {

    Report create(String type);

}
