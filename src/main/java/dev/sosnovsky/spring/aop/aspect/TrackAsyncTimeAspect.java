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
public class TrackAsyncTimeAspect {

    private final TimeService timeService;

    // todo проверить, что будет если проверяемый метод выдаст ошибку
    @Pointcut("@annotation(dev.sosnovsky.spring.aop.annotation.TrackAsyncTime)")
    public void anyMethodWithAnnotationTrackAsyncTime() {
    }

    @Around("anyMethodWithAnnotationTrackAsyncTime()")
    public Object aroundAnyMethodWithAnnotationTrackAsyncTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = proceedingJoinPoint.getSignature().getName();
        var result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // try
        timeService.asyncSaveMethodExecutionTime(methodName, executionTime);
        // catch

        log.info("Замерено {}", executionTime );

        return result;
    }
}