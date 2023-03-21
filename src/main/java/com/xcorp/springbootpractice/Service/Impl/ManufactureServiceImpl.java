package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.Model.Car;
import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Repository.CarRepository;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import com.xcorp.springbootpractice.Service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufactureServiceImpl implements ManufactureService {
    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Page<List<Manufacture>> getAllManufactures(Pageable pageable){
        return manufactureRepository.findAll(pageable).map(List::of);
    }

    private boolean checkHasId(String id){
        Optional<Manufacture> manufacture = manufactureRepository.findById(id);
        return manufacture.isPresent();
    }

    @Override
    public Manufacture createManufacture(Manufacture manufacture){
        return manufactureRepository.save(manufacture);
    }

    @Override
    public Manufacture updateManufacture(Manufacture newManufacture) throws Exception {
        return manufactureRepository.findById(newManufacture.getManufactureId()).map(manufacture -> {
            manufacture.setManufactureName(newManufacture.getManufactureName());
            manufacture.setManufactureAddress(newManufacture.getManufactureAddress());
            return manufactureRepository.save(manufacture);
        }).orElseGet(() -> {
            newManufacture.setManufactureId(newManufacture.getManufactureId());
            return manufactureRepository.save(newManufacture);
        });
    }

    @Override
    public String removeManufacture(String id) throws Exception {
        if(checkHasId(id)){
            List<Car> cars = carRepository.findByCarManufacture_ManufactureId(id);
            if(!cars.isEmpty()){
                throw new Exception("Manufacture has cars");
            }else{
                manufactureRepository.deleteById(id);
                return "Delete manufacture success";
            }
        }else{
            throw new Exception("Manufacture not found");
        }
    }
}
