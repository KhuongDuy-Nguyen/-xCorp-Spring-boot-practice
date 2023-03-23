package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.DTO.Interfaces.CarMapper;
import com.xcorp.springbootpractice.DTO.Request.Req_CarDTO;
import com.xcorp.springbootpractice.DTO.Response.Res_CarDTO;
import com.xcorp.springbootpractice.Exception.NotFoundException;
import com.xcorp.springbootpractice.Model.Car;
import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Model.Model;
import com.xcorp.springbootpractice.Repository.CarRepository;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import com.xcorp.springbootpractice.Repository.ModelRepository;
import com.xcorp.springbootpractice.Service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper resCarMapper;

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private ModelRepository modelRepository;


    @Override
    public Page<Res_CarDTO> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(resCarMapper::carToCarDTO);
    }

    @Override
    public Page<Res_CarDTO> filterCarByManufacture(String name, Pageable pageable) {
        return carRepository.findByCarManufacture_ManufactureName(name, pageable).map(resCarMapper::carToCarDTO);
    }

    @Override
    public Page<Res_CarDTO> searchCarName(String name, Pageable pageable) {
        return carRepository.findByCarNameIsContaining(name, pageable).map(resCarMapper::carToCarDTO);
    }


    private boolean checkHasCar(String id){
        Optional<Car> car = carRepository.findById(id);
        return car.isPresent();
    }
    
    @Override
    public Res_CarDTO createCar(Req_CarDTO newCar){
        Optional<Manufacture> manufacture = manufactureRepository.findById(newCar.getCarManufactureId());
        Optional<Model> model = modelRepository.findById(newCar.getCarModelId());

        if(model.isEmpty()) {
            throw new NotFoundException ("Model not found");
        }else if(manufacture.isEmpty()){
            throw new NotFoundException("Manufacture not found");
        }else{
            Car car = new Car();

            car.setCarName(newCar.getCarName());
            car.setBuyDate(newCar.getBuyDate());
            car.setCarManufacture(manufacture.get());
            car.setCarModel(model.get());

            carRepository.save(car);
            return resCarMapper.carToCarDTO(car);
        }
    }

    @Override
    public Res_CarDTO updateCar(Req_CarDTO newCar){
        if(checkHasCar(newCar.getCarId())){

            Optional<Manufacture> manufacture = manufactureRepository.findById(newCar.getCarManufactureId());
            Optional<Model> model = modelRepository.findById(newCar.getCarModelId());

            if(model.isEmpty()) {
                throw new NotFoundException("Model not found");
            }else if(manufacture.isEmpty()){
                throw new NotFoundException("Manufacture not found");
            }else{
                Car oldCar = carRepository.findByCarId(newCar.getCarId());

                oldCar.setCarName(newCar.getCarName());
                oldCar.setBuyDate(newCar.getBuyDate());
                oldCar.setCarManufacture(manufacture.get());
                oldCar.setCarModel(model.get());
                carRepository.save(oldCar);

                return resCarMapper.carToCarDTO(oldCar);
            }
        }
        else{
            throw new NotFoundException("Car not found");
        }
    }

    
    @Override
    public String removeCar(String id){
        if(checkHasCar(id)){
            carRepository.deleteById(id);
            return "Delete success";
        }else{
            throw new NotFoundException("Car not found");
        }
    }
}
