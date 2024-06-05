package dev.sosnovsky.spring.aop.controller;

import dev.sosnovsky.spring.aop.dto.ExecutionTimeDto;
import dev.sosnovsky.spring.aop.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time")
public class TimeController {

    private final TimeService timeService;

    @Operation(summary = "Получение среднего времени выполнения по имени метода")
    @GetMapping("/avg/{methodName}")
    public ExecutionTimeDto findAverageExecutionTimeByMethodName(@PathVariable String methodName) {
        return timeService.findAverageExecutionTimeByMethodName(methodName);
    }

    @Operation(summary = "Получение максимального времени выполнения по имени метода")
    @GetMapping("/max/{methodName}")
    public ExecutionTimeDto findMaxExecutionTimeByMethodName(@PathVariable String methodName) {
        return timeService.findMaxExecutionTimeByMethodName(methodName);
    }

    @Operation(summary = "Получение среднего времени выполнения всех методов начинающихся с заданного значения")
    @GetMapping("/avg")
    public ExecutionTimeDto findAverageExecutionTimeOfMethods(
            @RequestParam(value = "methodNameStartsWith", required = false, defaultValue = "")
            String methodNameStartsWith) {
        return timeService.findAverageExecutionTimeOfMethods(methodNameStartsWith);
    }

    @Operation(summary = "Очистка статистики")
    @DeleteMapping()
    public void clearStatistic() {
        timeService.clearStatistic();
    }
}
