package com.kalinowski.test5.model.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class ReportPerOperator extends Report {

    private int plus;
    private int minus;
    private int divide;
    private int multiply;
}
