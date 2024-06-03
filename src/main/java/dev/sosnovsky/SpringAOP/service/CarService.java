package dev.sosnovsky.SpringAOP.service;

import dev.sosnovsky.SpringAOP.model.Car;
import dev.sosnovsky.SpringAOP.model.TypeCar;

import java.util.List;

public interface CarService {
    void addCar(Car car);
    void addCars(List<Car> newCar);
    Car getCarByModel(String model);
    List<Car> getCarsByType(TypeCar typeCar);
    List<Car> getAllCars();
}
