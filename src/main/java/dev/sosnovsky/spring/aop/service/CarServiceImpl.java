package dev.sosnovsky.spring.aop.service;

import dev.sosnovsky.spring.aop.annotation.TrackAsyncTime;
import dev.sosnovsky.spring.aop.annotation.TrackTime;
import dev.sosnovsky.spring.aop.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    private final Map<String, Car> cars = new HashMap<>();

    @Override
    @TrackTime
    public void addCar(Car car) {
        randomDelay();
        cars.put(car.getVinNumber(), car);
    }

    @Override
    @TrackAsyncTime
    public void addCars(List<Car> newCar) {
        randomDelay();
        cars.putAll(newCar.stream().collect(Collectors.toMap(Car::getVinNumber, car -> car)));
    }

    @Override
    @TrackTime
    public Car getCarByVinNumber(String vinNumber) {
        randomDelay();
        return cars.get(vinNumber);
    }

    @Override
    @TrackAsyncTime
    public List<Car> getAllCars() {
        randomDelay();
        return cars.values().stream().toList();
    }

    private void randomDelay() {
        long delayTime = new Random().nextLong(500, 1000);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            log.error("The error was occurred", e);
        }
    }
}
