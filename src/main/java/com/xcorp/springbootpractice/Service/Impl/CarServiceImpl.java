package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.Model.*;
import com.xcorp.springbootpractice.Repository.CarRepository;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import com.xcorp.springbootpractice.Repository.ModelRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements com.xcorp.springbootpractice.Service.CarService {
    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;

    private final ManufactureRepository manufactureRepository;

    private final ModelRepository modelRepository;

    public CarServiceImpl(CarRepository carRepository, ManufactureRepository manufactureRepository, ModelRepository modelRepository) {
        this.carRepository = carRepository;
        this.manufactureRepository = manufactureRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public Page<List<Car>> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(List::of);
    }

    private boolean checkHasCar(String id){
        Optional<Car> car = carRepository.findById(id);
        return car.isPresent();
    }

    private boolean checkHasManuAndModel(String idManu, String idModel){
        Optional<Manufacture> manu = manufactureRepository.findById(idManu);
        Optional<Model> model = modelRepository.findById(idModel);
        return manu.isPresent() && model.isPresent();
    }

    
    @Override
    public Car createCar(Car newCar) throws Exception {
        if(checkHasManuAndModel(newCar.getManufactureId().getId(), newCar.getModel().getId())) {
            newCar.setManufactureId(manufactureRepository.findManufactureById(newCar.getManufactureId().getId()));
            return carRepository.save(newCar);
        }else{
            throw new Exception("Manufacture or model not found");
        }
    }

    
    @Override
    public Car updateCar(Car newCar) throws Exception {
        if(checkHasCar(newCar.getId())){
            Car oldCar = carRepository.findCarById(newCar.getId());

            oldCar.setName(newCar.getName());
            oldCar.setModel(newCar.getModel());

            String manuId = newCar.getManufactureId().getId();
            oldCar.setBuyDate(newCar.getBuyDate());

            if(checkHasManuAndModel(newCar.getManufactureId().getId(), newCar.getModel().getId())){
                oldCar.setManufactureId(manufactureRepository.findManufactureById(manuId));
            }else{
                throw new Exception("Manufacture or model not found");
            }
            return carRepository.save(oldCar);
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
