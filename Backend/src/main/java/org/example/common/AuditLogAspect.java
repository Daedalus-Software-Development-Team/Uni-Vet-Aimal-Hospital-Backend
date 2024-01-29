package org.example.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AuditLogAspect {
    @Around("@annotation(AuditTime)")
    public Object auditExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //before starting method
        long startTime=System.currentTimeMillis();
        //method is run
        Object proceed=joinPoint.proceed();

        //after finishing method
        long endTime=System.currentTimeMillis()-startTime;
        log.info("Execution Time {}ms",endTime);

        return proceed;
    }
}
