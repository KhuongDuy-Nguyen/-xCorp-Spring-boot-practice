package com.xcorp.springbootpractice;

import com.xcorp.springbootpractice.Model.Car;
import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Model.Model;
import com.xcorp.springbootpractice.Repository.CarRepository;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import com.xcorp.springbootpractice.Repository.ModelRepository;
import java.util.Date;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for initializing the database with sample data using CommandLineRunner.
 */
@Configuration
public class LoadDatabase {
  /**
   * Initializes the database with sample data.
   *
   * @param carRepository Repository for Car entity
   * @param manufactureRepository Repository for Manufacture entity
   * @param modelRepository Repository for Model entity
   * @return A CommandLineRunner to insert sample data into the database
   */
  @Bean
  CommandLineRunner initDatabase(
      CarRepository carRepository,
      ManufactureRepository manufactureRepository,
      ModelRepository modelRepository) {
    return args -> {
      carRepository.deleteAll();
      manufactureRepository.deleteAll();

      Random random = new Random();

      // Insert Manufacture and model
      for (int i = 1; i <= 5; i++) {
        manufactureRepository.save(new Manufacture("Name " + i, "Address " + i));
        modelRepository.save(new Model("Model " + i));
      }

      // Insert Car
      for (int i = 1; i <= 10; i++) {
        carRepository.save(
            new Car(
                "Car " + i,
                modelRepository.findAll().get(random.nextInt(5)),
                manufactureRepository.findAll().get(random.nextInt(5)),
                randomDate()));
      }
    };
  }

  /**
   * Generates a random date.
   *
   * @return A random date
   */
  private Date randomDate() {
    Random r = new Random();
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.set(java.util.Calendar.MONTH, Math.abs(r.nextInt()) % 12);
    c.set(java.util.Calendar.DAY_OF_MONTH, Math.abs(r.nextInt()) % 30);
    c.setLenient(true);
    return c.getTime();
  }
}
