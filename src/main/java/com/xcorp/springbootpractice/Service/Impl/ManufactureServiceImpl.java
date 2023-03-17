package com.xcorp.springbootpractice.Service.Impl;

import com.xcorp.springbootpractice.Model.Manufacture;
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
    public Manufacture updateManufacture(Manufacture newManufacture, String id) throws Exception {
        return manufactureRepository.findById(id).map(manufacture -> {
            manufacture.setName(newManufacture.getName());
            manufacture.setAddress(newManufacture.getAddress());
            return manufactureRepository.save(manufacture);
        }).orElseGet(() -> {
            newManufacture.setId(id);
            return manufactureRepository.save(newManufacture);
        });
    }

    @Override
    public String removeManufacture(String id) throws Exception {
        if(checkHasId(id)){
            manufactureRepository.deleteById(id);
            return "Delete success";
        }else{
            throw new Exception("Manufacture not found");
        }
    }
}
