package org.example.action;

import org.example.ds.Report;
import org.example.format.ReportFormatter;
import org.example.logger.PerformanceLogger;
import org.example.provider.ReportProvider;
import org.example.repository.ReportRepository;
import org.springframework.stereotype.Service;
import static org.example.logger.PerformanceLogger.*;

@Service
public class ComplexAction {
    private ReportRepository reportRepository;
    private ReportProvider reportProvider;
    private ReportFormatter reportFormatter;

    public ComplexAction(ReportRepository reportRepository, ReportProvider reportProvider, ReportFormatter reportFormatter) {
        this.reportRepository = reportRepository;
        this.reportProvider = reportProvider;
        this.reportFormatter = reportFormatter;
    }

    public void complexAction() {
        Report report=reportProvider.getReport();
        Report formattedReport = reportFormatter.formatReport(report);
        reportRepository.save(report);

//        PerformanceLogger performanceLogger = new PerformanceLogger();
//        PerformanceLoggerInfo performanceLoggerInfo = performanceLogger
//                .start("reportProvider.getReport");
//        Report report = reportProvider.getReport();
//        performanceLogger.end(performanceLoggerInfo);
//
//        performanceLoggerInfo = performanceLogger.start("reportFormatter.getReport");
//        Report formattedReport = reportFormatter.formatReport(report);
//        performanceLogger.end(performanceLoggerInfo);
//
//        performanceLoggerInfo = performance
//        Logger.start("reportRepository.getReport");
//        reportRepository.save(formattedReport);
//        performanceLogger.end(performanceLoggerInfo);
    }
}
