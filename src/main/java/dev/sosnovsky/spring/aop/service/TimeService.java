package dev.sosnovsky.spring.aop.service;

import dev.sosnovsky.spring.aop.dto.ExecutionTimeDto;

public interface TimeService {
    void saveMethodExecutionTime(String methodName, long executionTimeInMs);
    void asyncSaveMethodExecutionTime(String methodName, long executionTimeInMs) throws InterruptedException;

    ExecutionTimeDto findAverageExecutionTimeByMethodName(String methodName);

    ExecutionTimeDto findMaxExecutionTimeByMethodName(String methodName);

    ExecutionTimeDto findAverageExecutionTimeOfMethods(String methodNameStartsWith);

    ExecutionTimeDto findAverageExecutionTimeOfAllMethods();

    /*ExecutionTimeDto findExecutionTime(
            AggregateFunctions aggregateFunction, List<String> methodNames, String groupOfMethods);*/

    void clearStatistic();
}
