package dev.sosnovsky.SpringAOP.service;

import dev.sosnovsky.SpringAOP.dto.ExecutionTimeDto;
import dev.sosnovsky.SpringAOP.model.ExecutionTime;
import dev.sosnovsky.SpringAOP.repository.TimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TimeServiceImpl implements TimeService {

    private TimeRepository timeRepository;

    @Override
    public void saveMethodExecutionTime(String methodName, long executionTimeInMs) {
        ExecutionTime executionTime = new ExecutionTime(methodName, executionTimeInMs);
        timeRepository.save(executionTime);
    }

    @Override
    public ExecutionTimeDto findAverageTimeExecutionMethodByMethodName(String methodName) {
        return new ExecutionTimeDto(timeRepository.findAverageTimeExecutionMethodByMethodName(methodName));
    }

    @Override
    public ExecutionTimeDto findMaxTimeExecutionMethodByMethodName(String methodName) {
        return new ExecutionTimeDto(timeRepository.findMaxTimeExecutionMethodByMethodName(methodName));
    }

    @Override
    public ExecutionTimeDto findAverageTimeExecutionAllGetMethods() {
        return new ExecutionTimeDto(timeRepository.findAverageTimeExecutionAllGetMethods());
    }

    @Override
    public ExecutionTimeDto findAverageTimeExecutionAllMethods() {
        return new ExecutionTimeDto(timeRepository.findAverageTimeExecutionAllMethods());
    }

    @Override
    public void clearStatistic() {
        timeRepository.clearTable();
    }
}
