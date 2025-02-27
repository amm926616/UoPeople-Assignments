package org.example.repository;

import org.example.annotation.PerformanceLogger;
import org.example.ds.Report;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepository {
    @PerformanceLogger
    public void save(Report report) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Report saved to the database.");
    }
}
