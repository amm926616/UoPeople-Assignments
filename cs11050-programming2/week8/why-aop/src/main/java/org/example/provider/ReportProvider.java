package org.example.provider;

import org.example.annotation.PerformanceLogger;
import org.example.ds.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportProvider {
    @PerformanceLogger
    public Report getReport() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ReportProvider.getReport");
        return new Report();
    }
}
