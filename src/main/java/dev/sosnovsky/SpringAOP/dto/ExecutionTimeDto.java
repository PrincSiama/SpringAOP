package dev.sosnovsky.SpringAOP.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionTimeDto {

    private Double executionTimeInMs;
}
