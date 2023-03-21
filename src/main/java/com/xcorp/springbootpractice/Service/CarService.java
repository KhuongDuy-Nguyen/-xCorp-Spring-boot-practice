package com.xcorp.springbootpractice.Service;

import com.xcorp.springbootpractice.Model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    Page<List<Car>> getAllCars(Pageable pageable);
    Page<List<Car>> filterCarByManufacture(String name, Pageable pageable);
    Page<List<Car>> searchCarName(String name, Pageable pageable);

    Car createCar(Car newCar) throws Exception;

    Car updateCar(Car newCar) throws Exception;

    String removeCar(String id) throws Exception;
}
