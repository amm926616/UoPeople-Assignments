package org.example.format;

import org.example.annotation.PerformanceLogger;
import org.example.ds.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportFormatter {
    @PerformanceLogger
    public Report formatReport(Report report) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ReportFormatter.formatReport");
        return report;
    }
}
