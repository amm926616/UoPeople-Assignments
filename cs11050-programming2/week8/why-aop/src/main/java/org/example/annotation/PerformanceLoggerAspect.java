package org.example.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggerAspect {

    @Around("@annotation(org.example.annotation.PerformanceLogger)")
    public Object logger(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        }
        finally {
            long end = System.currentTimeMillis();
            System.out.println(String.format("%s method took %s ms.%n",
                    joinPoint.getSignature().getName(),
                    (end - start)));
        }
    }
}
