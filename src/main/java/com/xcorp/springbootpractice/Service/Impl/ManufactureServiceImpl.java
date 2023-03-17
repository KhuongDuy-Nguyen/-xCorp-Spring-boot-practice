package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Repository.ManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufactureServiceImpl implements com.xcorp.springbootpractice.Service.ManufactureService {
    @Autowired
    private ManufactureRepository manufactureRepository;

    @Override
    public List<Manufacture> getAllManufactures(){
        return manufactureRepository.findAll();
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
        if(checkHasId(newManufacture.getId())){
            Manufacture oldManufacture = manufactureRepository.getManufactureById(newManufacture.getId());

            oldManufacture.setName(newManufacture.getName());
            oldManufacture.setAddress(newManufacture.getAddress());
            return manufactureRepository.save(oldManufacture);
        }else{
            throw new Exception("Manufacture not found");
        }
    }

    @Override
    public List<Manufacture> removeManufacture(String id) throws Exception {
        if(checkHasId(id)){
            manufactureRepository.deleteById(id);
            return getAllManufactures();
        }else{
            throw new Exception("Manufacture not found");
        }
    }
}
