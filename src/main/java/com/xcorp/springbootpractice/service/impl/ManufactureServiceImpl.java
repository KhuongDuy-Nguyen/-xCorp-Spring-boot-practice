package com.xcorp.springbootpractice.service.impl;

import com.xcorp.springbootpractice.dto.interfaces.ManufactureMapper;
import com.xcorp.springbootpractice.dto.request.ReqManufactureDto;
import com.xcorp.springbootpractice.dto.response.ResManufactureDto;
import com.xcorp.springbootpractice.exception.HasExistException;
import com.xcorp.springbootpractice.exception.NotFoundException;
import com.xcorp.springbootpractice.entity.Car;
import com.xcorp.springbootpractice.entity.Manufacture;
import com.xcorp.springbootpractice.repository.CarRepository;
import com.xcorp.springbootpractice.repository.ManufactureRepository;
import com.xcorp.springbootpractice.service.ManufactureService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManufactureServiceImpl implements ManufactureService {
  @Autowired private ManufactureRepository manufactureRepository;

  @Autowired private ManufactureMapper resManufactureMapper;

  @Autowired private CarRepository carRepository;

  @Override
  public Page<ResManufactureDto> getAllManufactures(Pageable pageable) {
    return manufactureRepository
        .findAll(pageable)
        .map(resManufactureMapper::manufactureToManufactureDto);
  }

  private boolean checkHasId(String id) {
    Optional<Manufacture> manufacture = manufactureRepository.findById(id);
    return manufacture.isPresent();
  }

  @Override
  public ResManufactureDto createManufacture(ReqManufactureDto newManufacture) {
    Manufacture manufacture = new Manufacture();
    manufacture.setManufactureName(newManufacture.getManufactureName());
    manufacture.setManufactureAddress(newManufacture.getManufactureAddress());
    manufactureRepository.save(manufacture);

    return resManufactureMapper.manufactureToManufactureDto(manufacture);
  }

  @Override
  public ResManufactureDto updateManufacture(ReqManufactureDto newManufacture) {
    Optional<Manufacture> manufacture =
        manufactureRepository.findById(newManufacture.getManufactureId());

    if (manufacture.isPresent()) {
      manufacture.get().setManufactureName(newManufacture.getManufactureName());
      manufacture.get().setManufactureAddress(newManufacture.getManufactureAddress());
      manufactureRepository.save(manufacture.get());
      return resManufactureMapper.manufactureToManufactureDto(manufacture.get());
    } else {
      throw new NotFoundException("Manufacture not found");
    }
  }

  @Override
  public String removeManufacture(String id) {
    if (checkHasId(id)) {
      List<Car> cars = carRepository.findByCarManufacture_ManufactureId(id);
      if (!cars.isEmpty()) {
        throw new HasExistException("Manufacture has cars");
      } else {
        manufactureRepository.deleteById(id);
        return "Delete manufacture success";
      }
    } else {
      throw new NotFoundException("Manufacture not found");
    }
  }
}
