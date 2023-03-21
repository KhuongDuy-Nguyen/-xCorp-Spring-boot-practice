package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.Model.*;
import com.xcorp.springbootpractice.Repository.CarRepository;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import com.xcorp.springbootpractice.Repository.ModelRepository;

import com.xcorp.springbootpractice.Service.CarService;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private ModelRepository modelRepository;


    @Override
    public Page<List<Car>> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(List::of);
    }

    @Override
    public Page<List<Car>> filterCarByManufacture(String name, Pageable pageable) {
        return carRepository.findByCarManufacture_ManufactureName(name, pageable);
    }

    @Override
    public Page<List<Car>> searchCarName(String name, Pageable pageable) {
        return carRepository.findByCarNameIsContaining(name, pageable);
    }


    private boolean checkHasCar(String id){
        Optional<Car> car = carRepository.findById(id);
        return car.isPresent();
    }

    private Map<String, Object> checkHasManuAndModel(String nameManu, String nameModel){

        Map<String, Object> result = new HashMap<>();

        Optional<Manufacture> manu = Optional.ofNullable(manufactureRepository.findByManufactureName(nameManu));
        Optional<Model> model = Optional.ofNullable(modelRepository.findByModelName(nameModel));

        if (manu.isPresent() && model.isPresent()) {
            result.put("manufacture", manu.get());
            result.put("model", model.get());
            return result;
        }else {
            return Collections.emptyMap();
        }
    }

    
    @Override
    public Car createCar(Car newCar) throws Exception {
        String manuName = newCar.getCarManufacture().getManufactureName();
        String modelName = newCar.getCarModel().getModelName();

        Map<String, Object> check = checkHasManuAndModel(manuName, modelName);

        if(!check.isEmpty()) {
            logger.info("Manufacture and model found");
            newCar.setCarManufacture((Manufacture) check.get("manufacture"));
            newCar.setCarModel((Model) check.get("model"));
            return carRepository.save(newCar);
        }else{
            throw new Exception("Manufacture or model not found");
        }
    }

    @Override
    public Car updateCar(Car newCar) throws Exception {
        if(checkHasCar(newCar.getCarId())){
            Car oldCar = carRepository.findByCarId(newCar.getCarId());

            String manuName = newCar.getCarManufacture().getManufactureName();
            String modelName = newCar.getCarModel().getModelName();

            Map<String, Object> check = checkHasManuAndModel(manuName, modelName);

            oldCar.setCarName(newCar.getCarName());
            oldCar.setBuyDate(newCar.getBuyDate());
            if (!check.isEmpty()) {
                oldCar.setCarManufacture((Manufacture) check.get("manufacture"));
                oldCar.setCarModel((Model) check.get("model"));

                return carRepository.save(oldCar);
            }else{
                throw new Exception("Manufacture or model not found");
            }
        }
        else{
            throw new Exception("Car not found");
        }
    }

    
    @Override
    public String removeCar(String id) throws Exception {
        if(checkHasCar(id)){
            carRepository.deleteById(id);
            return "Delete success";
        }else{
            throw new Exception("Car not found");
        }
    }
}
