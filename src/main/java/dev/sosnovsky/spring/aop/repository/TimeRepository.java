package dev.sosnovsky.spring.aop.repository;

import dev.sosnovsky.spring.aop.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<ExecutionTime, Long> {

    @Query(value =
            "SELECT round(AVG(executionTimeInMs), 2) " +
                    "FROM ExecutionTime " +
                    "WHERE methodName = :methodName")
    Double findAverageExecutionTimeByMethodName(String methodName);

    @Query(value =
            "SELECT MAX(executionTimeInMs) " +
                    "FROM ExecutionTime " +
                    "WHERE methodName = :methodName")
    Double findMaxExecutionTimeByMethodName(String methodName);

    @Query(value =
            "SELECT round(AVG(executionTimeInMs), 2) " +
                    "FROM ExecutionTime " +
                    "WHERE methodName LIKE :methodNameStartsWith%")
    Double findAverageExecutionTimeOfMethods(String methodNameStartsWith);

    @Query(value =
            "SELECT round(AVG(executionTimeInMs), 2) " +
                    "FROM ExecutionTime")
    Double findAverageExecutionTimeOfAllMethods();

    @Query(value = "TRUNCATE method_execution_time RESTART IDENTITY", nativeQuery = true)
    void clearTable();
}
