package com.xcorp.springbootpractice;

import com.xcorp.springbootpractice.Model.Car;
import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Repository.CarRepository;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Random;


@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CarRepository carRepository, ManufactureRepository manufactureRepository) {
        return args -> {
            carRepository.deleteAll();
            manufactureRepository.deleteAll();

            Random random = new Random();

            // Insert Manufacture
            for(int i = 0; i < 5; i++){
                manufactureRepository.save(
                        new Manufacture(
                                "Name " + i,
                                "Address " + i
                        ));
            }

            // Insert Car
            for(int i = 0; i < 10; i++){
                carRepository.save(
                        new Car("Car " + i,
                                "Model" + i,
                                manufactureRepository.findAll().get(random.nextInt(5)),
                                new Date()));
            }
        };
    }
}
