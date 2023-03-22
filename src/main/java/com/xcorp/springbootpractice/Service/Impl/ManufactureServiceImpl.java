package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.DTO.Interfaces.ManufactureMapper;
import com.xcorp.springbootpractice.DTO.Request.Req_ManufactureDTO;
import com.xcorp.springbootpractice.DTO.Response.Res_ManufactureDTO;
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
    private ManufactureMapper resManufactureMapper;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Page<Res_ManufactureDTO> getAllManufactures(Pageable pageable){
        return manufactureRepository.findAll(pageable).map(resManufactureMapper::manufactureToManufactureDTO);
    }

    private boolean checkHasId(String id){
        Optional<Manufacture> manufacture = manufactureRepository.findById(id);
        return manufacture.isPresent();
    }

    @Override
    public Res_ManufactureDTO createManufacture(Req_ManufactureDTO newManufacture){
        Manufacture manufacture = new Manufacture();
        manufacture.setManufactureName(newManufacture.getManufactureName());
        manufacture.setManufactureAddress(newManufacture.getManufactureAddress());
        manufactureRepository.save(manufacture);

        return resManufactureMapper.manufactureToManufactureDTO(manufacture);
    }

    @Override
    public Res_ManufactureDTO updateManufacture(Req_ManufactureDTO newManufacture) throws Exception {
        Optional<Manufacture> manufacture = manufactureRepository.findById(newManufacture.getManufactureId());

        if(manufacture.isPresent()){
            manufacture.get().setManufactureName(newManufacture.getManufactureName());
            manufacture.get().setManufactureAddress(newManufacture.getManufactureAddress());
            manufactureRepository.save(manufacture.get());
            return resManufactureMapper.manufactureToManufactureDTO(manufacture.get());
        }else{
            throw new Exception("Manufacture not found");
        }

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
