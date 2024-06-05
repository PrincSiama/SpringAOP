package dev.sosnovsky.spring.aop.service;

import dev.sosnovsky.spring.aop.model.Car;

import java.util.List;

public interface CarService {
    void addCar(Car car);

    void addCars(List<Car> newCar);

    Car getCarByVinNumber(String vinNumber);

    List<Car> getAllCars();
}
