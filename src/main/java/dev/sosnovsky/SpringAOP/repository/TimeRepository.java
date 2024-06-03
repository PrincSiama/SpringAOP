package dev.sosnovsky.SpringAOP.repository;

import dev.sosnovsky.SpringAOP.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimeRepository extends JpaRepository<ExecutionTime, Long> {

    @Query(value =
            "SELECT round(AVG(executionTimeInMs), 2) " +
            "FROM ExecutionTime " +
            "WHERE methodName = :methodName")
    Double findAverageTimeExecutionMethodByMethodName(String methodName);

    @Query(value =
            "SELECT MAX(executionTimeInMs) " +
                    "FROM ExecutionTime " +
                    "WHERE methodName = :methodName")
    Double findMaxTimeExecutionMethodByMethodName(String methodName);

    @Query(value =
            "SELECT round(AVG(executionTimeInMs), 2) " +
                    "FROM ExecutionTime " +
                    "WHERE methodName LIKE 'get%'")
    Double findAverageTimeExecutionAllGetMethods();

    @Query(value =
            "SELECT round(AVG(executionTimeInMs), 2) " +
                    "FROM ExecutionTime ")
    Double findAverageTimeExecutionAllMethods();

    @Query(value = "TRUNCATE method_execution_time RESTART IDENTITY", nativeQuery = true)
    void clearTable();
}
