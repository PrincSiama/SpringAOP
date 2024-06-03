package dev.sosnovsky.SpringAOP.service;

import dev.sosnovsky.SpringAOP.annotation.TrackTime;
import dev.sosnovsky.SpringAOP.model.Car;
import dev.sosnovsky.SpringAOP.model.TypeCar;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final Map<String, Car> cars = new HashMap<>();

    @Override
    @TrackTime
    public void addCar(Car car) {
        delay();
        cars.put(car.getModel(), car);
    }

    @Override
    @TrackTime
    public void addCars(List<Car> newCar) {
        delay();
        cars.putAll(newCar.stream().collect(Collectors.toMap(Car::getModel, car -> car)));
    }

    @Override
    @TrackTime
    public Car getCarByModel(String model) {
        delay();
        return cars.get(model);
    }

    @Override
    @TrackTime
    public List<Car> getCarsByType(TypeCar typeCar) {
        delay();
        return cars.values().stream().filter(Car -> Car.getTypeCar().equals(typeCar)).collect(Collectors.toList());
    }

    @Override
    @TrackTime
    public List<Car> getAllCars() {
        delay();
        return cars.values().stream().toList();
    }

    private void delay() {
        long delayTime = new Random().nextLong(500, 1000);
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
        }
    }
}
