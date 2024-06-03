package dev.sosnovsky.SpringAOP.service;

import dev.sosnovsky.SpringAOP.dto.ExecutionTimeDto;

public interface TimeService {
    void saveMethodExecutionTime(String methodName, long executionTimeInMs);
    ExecutionTimeDto findAverageTimeExecutionMethodByMethodName(String methodName);
    ExecutionTimeDto findMaxTimeExecutionMethodByMethodName(String methodName);
    ExecutionTimeDto findAverageTimeExecutionAllGetMethods();
    ExecutionTimeDto findAverageTimeExecutionAllMethods();
    void clearStatistic();
}
