package com.xcorp.springbootpractice.Repository;

import com.xcorp.springbootpractice.Model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {

    Car findByCarId(String id);

    List<Car> findByCarManufacture_ManufactureId(String id);

    Page<List<Car>> findByCarManufacture_ManufactureName(String name, Pageable pageable);

    Page<List<Car>> findByCarNameIsContaining(String name, Pageable pageable);
}