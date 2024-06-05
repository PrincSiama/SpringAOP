package dev.sosnovsky.spring.aop.service;

import dev.sosnovsky.spring.aop.dto.ExecutionTimeDto;
import dev.sosnovsky.spring.aop.model.ExecutionTime;
import dev.sosnovsky.spring.aop.repository.TimeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TimeServiceImpl implements TimeService {

    private TimeRepository timeRepository;

    @Override
    public void saveMethodExecutionTime(String methodName, long executionTimeInMs) {
        ExecutionTime executionTime = new ExecutionTime(methodName, executionTimeInMs);
        timeRepository.save(executionTime);
    }

    @Override
    @Async
    public void asyncSaveMethodExecutionTime(String methodName, long executionTimeInMs) throws InterruptedException {
        Thread.sleep(500);
        ExecutionTime executionTime = new ExecutionTime(methodName, executionTimeInMs);
        timeRepository.save(executionTime);
        log.info("Сохранено {}", executionTime);
    }

    @Override
    public ExecutionTimeDto findAverageExecutionTimeByMethodName(String methodName) {
        return new ExecutionTimeDto(timeRepository.findAverageExecutionTimeByMethodName(methodName));
    }

    @Override
    public ExecutionTimeDto findMaxExecutionTimeByMethodName(String methodName) {
        return new ExecutionTimeDto(timeRepository.findMaxExecutionTimeByMethodName(methodName));
    }

    @Override
    public ExecutionTimeDto findAverageExecutionTimeOfMethods(String methodNameStartsWith) {
        return new ExecutionTimeDto(timeRepository.findAverageExecutionTimeOfMethods(methodNameStartsWith));
    }

    @Override
    public ExecutionTimeDto findAverageExecutionTimeOfAllMethods() {
        return new ExecutionTimeDto(timeRepository.findAverageExecutionTimeOfAllMethods());
    }

    @Override
    public void clearStatistic() {
        timeRepository.clearTable();
        log.info("The statistics were cleared");
    }
}
