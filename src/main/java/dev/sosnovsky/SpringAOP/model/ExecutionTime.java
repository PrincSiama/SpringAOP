package dev.sosnovsky.SpringAOP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "method_execution_time")
public class ExecutionTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "execution_time_ms")
    private long executionTimeInMs;

    public ExecutionTime(String methodName, long executionTimeInMs) {
        this.methodName = methodName;
        this.executionTimeInMs = executionTimeInMs;
    }
}
