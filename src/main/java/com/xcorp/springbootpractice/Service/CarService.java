package com.xcorp.springbootpractice.Service;

import com.xcorp.springbootpractice.Model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car createCar(Car newCar) throws Exception;

    Car updateCar(Car newCar) throws Exception;

    List<Car> removeCar(String id) throws Exception;
}
