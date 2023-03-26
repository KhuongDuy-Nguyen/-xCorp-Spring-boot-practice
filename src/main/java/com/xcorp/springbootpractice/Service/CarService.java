package com.xcorp.springbootpractice.Service;

import com.xcorp.springbootpractice.DTO.Request.ReqCarDto;
import com.xcorp.springbootpractice.DTO.Response.ResCarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
  Page<ResCarDto> getAllCars(Pageable pageable);

  Page<ResCarDto> filterCarByManufacture(String name, Pageable pageable);

  Page<ResCarDto> searchCarName(String name, Pageable pageable);

  ResCarDto createCar(ReqCarDto newCar);

  ResCarDto updateCar(ReqCarDto newCar);

  String removeCar(String id);
}
