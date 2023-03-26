package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.DTO.Interfaces.CarMapper;
import com.xcorp.springbootpractice.DTO.Request.ReqCarDto;
import com.xcorp.springbootpractice.DTO.Response.ResCarDto;
import com.xcorp.springbootpractice.Exception.NotFoundException;
import com.xcorp.springbootpractice.Model.Car;
import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Model.Model;
import com.xcorp.springbootpractice.Repository.CarRepository;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import com.xcorp.springbootpractice.Repository.ModelRepository;
import com.xcorp.springbootpractice.Service.CarService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @description : Car Service Implementation
 * @project : springboot-practice
 * @package : com.xcorp.springbootpractice.Service.Impl
 * @class : CarServiceImpl
 */
@Service
public class CarServiceImpl implements CarService {

  @Autowired private CarRepository carRepository;

  @Autowired private CarMapper resCarMapper;

  @Autowired private ManufactureRepository manufactureRepository;

  @Autowired private ModelRepository modelRepository;

  @Override
  public Page<ResCarDto> getAllCars(Pageable pageable) {
    return carRepository.findAll(pageable).map(resCarMapper::carToCarDto);
  }

  @Override
  public Page<ResCarDto> filterCarByManufacture(String name, Pageable pageable) {
    return carRepository
        .findByCarManufacture_ManufactureName(name, pageable)
        .map(resCarMapper::carToCarDto);
  }

  @Override
  public Page<ResCarDto> searchCarName(String name, Pageable pageable) {
    return carRepository.findByCarNameIsContaining(name, pageable).map(resCarMapper::carToCarDto);
  }

  private boolean checkHasCar(String id) {
    Optional<Car> car = carRepository.findById(id);
    return car.isPresent();
  }

  @Override
  public ResCarDto createCar(ReqCarDto newCar) {
    Optional<Manufacture> manufacture =
        manufactureRepository.findById(newCar.getCarManufactureId());
    Optional<Model> model = modelRepository.findById(newCar.getCarModelId());

    if (model.isEmpty()) {
      throw new NotFoundException("Model not found");
    } else if (manufacture.isEmpty()) {
      throw new NotFoundException("Manufacture not found");
    } else {
      Car car = new Car();

      car.setCarName(newCar.getCarName());
      car.setBuyDate(newCar.getBuyDate());
      car.setCarManufacture(manufacture.get());
      car.setCarModel(model.get());

      carRepository.save(car);
      return resCarMapper.carToCarDto(car);
    }
  }

  @Override
  public ResCarDto updateCar(ReqCarDto newCar) {
    if (checkHasCar(newCar.getCarId())) {

      Optional<Manufacture> manufacture =
          manufactureRepository.findById(newCar.getCarManufactureId());
      Optional<Model> model = modelRepository.findById(newCar.getCarModelId());

      if (model.isEmpty()) {
        throw new NotFoundException("Model not found");
      } else if (manufacture.isEmpty()) {
        throw new NotFoundException("Manufacture not found");
      } else {
        Car oldCar = carRepository.findByCarId(newCar.getCarId());

        oldCar.setCarName(newCar.getCarName());
        oldCar.setBuyDate(newCar.getBuyDate());
        oldCar.setCarManufacture(manufacture.get());
        oldCar.setCarModel(model.get());
        carRepository.save(oldCar);

        return resCarMapper.carToCarDto(oldCar);
      }
    } else {
      throw new NotFoundException("Car not found");
    }
  }

  @Override
  public String removeCar(String id) {
    if (checkHasCar(id)) {
      carRepository.deleteById(id);
      return "Delete success";
    } else {
      throw new NotFoundException("Car not found");
    }
  }
}
