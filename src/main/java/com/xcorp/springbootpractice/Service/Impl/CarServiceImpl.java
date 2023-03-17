package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.Model.Car;
import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Repository.CarRepository;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements com.xcorp.springbootpractice.Service.CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Override
    public Page<List<Car>> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(List::of);
    }

    private boolean checkHasCar(String id){
        Optional<Car> car = carRepository.findById(id);
        return car.isPresent();
    }

    private boolean checkHasManu(String id){
        Optional<Manufacture> manu = manufactureRepository.findById(id);
        return manu.isPresent();
    }

    
    @Override
    public Car createCar(Car newCar) throws Exception {
        if(checkHasManu(newCar.getManufactureId().getId())) {
            newCar.setManufactureId(manufactureRepository.findManufactureById(newCar.getManufactureId().getId()));
            return carRepository.save(newCar);
        }else{
            throw new Exception("Manufacture not found");
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

            if(checkHasManu(manuId)){
                oldCar.setManufactureId(manufactureRepository.findManufactureById(manuId));
            }else{
                throw new Exception("Manufacture not found");
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