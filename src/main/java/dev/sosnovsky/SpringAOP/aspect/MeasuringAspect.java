package dev.sosnovsky.SpringAOP.aspect;

import dev.sosnovsky.SpringAOP.service.TimeService;
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
public class MeasuringAspect {

    private final TimeService timeService;

    @Pointcut("@annotation(dev.sosnovsky.SpringAOP.annotation.TrackTime)")
    public void anyMethodWithAnnotationTrackTime() {
    }

    @Around("anyMethodWithAnnotationTrackTime()")
    public Object aroundAnyMethodWithAnnotationTrackTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();

//        log.info("Начало выполнения метода {}", methodName);

        var result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

//        log.info("Метод {} выполнился за {} мс", methodName, endTime - startTime);

        long executionTime = endTime - startTime;

        timeService.saveMethodExecutionTime(methodName, executionTime);

        return result;
    }
}
