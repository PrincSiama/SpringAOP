package dev.sosnovsky.SpringAOP.controller;

import dev.sosnovsky.SpringAOP.dto.ExecutionTimeDto;
import dev.sosnovsky.SpringAOP.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time")
public class TimeController {

    private final TimeService timeService;

    @GetMapping("/avg/{methodName}")
    public ExecutionTimeDto findAverageTimeExecutionMethodByMethodName(@PathVariable String methodName) {
        return timeService.findAverageTimeExecutionMethodByMethodName(methodName);
    }

    @GetMapping("/max/{methodName}")
    public ExecutionTimeDto findMaxTimeExecutionMethodByMethodName(@PathVariable String methodName) {
        return timeService.findMaxTimeExecutionMethodByMethodName(methodName);
    }

    @GetMapping("/avg/get")
    public ExecutionTimeDto findAverageTimeExecutionAllGetMethods() {
        return timeService.findAverageTimeExecutionAllGetMethods();
    }

    @GetMapping("/avg/all")
    public ExecutionTimeDto findAverageTimeExecutionAllMethods() {
        return timeService.findAverageTimeExecutionAllMethods();
    }

    @DeleteMapping()
    public void clearStatistic() {
        timeService.clearStatistic();
    }

}
