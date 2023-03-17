package com.xcorp.springbootpractice.Repository;

import com.xcorp.springbootpractice.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {

    Car findCarById(String id);

    void deleteById(String id);

}