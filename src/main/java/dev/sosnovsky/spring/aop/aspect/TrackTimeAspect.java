package dev.sosnovsky.spring.aop.aspect;

import dev.sosnovsky.spring.aop.service.TimeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class TrackTimeAspect {

    private final TimeService timeService;

    @Pointcut("@annotation(dev.sosnovsky.spring.aop.annotation.TrackTime)")
    public void anyMethodWithAnnotationTrackTime() {
    }

    @Around("anyMethodWithAnnotationTrackTime()")
    public Object aroundAnyMethodWithAnnotationTrackTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = proceedingJoinPoint.getSignature().getName();
        var result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        try {
            timeService.saveMethodExecutionTime(methodName, executionTime);
        } catch (Exception e) {
            log.error("The error was occurred", e);
        }

        return result;
    }
}
