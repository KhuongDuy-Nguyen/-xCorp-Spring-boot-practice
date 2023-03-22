package com.xcorp.springbootpractice.Service;

import com.xcorp.springbootpractice.DTO.Request.Req_CarDTO;
import com.xcorp.springbootpractice.DTO.Response.Res_CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    Page<Res_CarDTO> getAllCars(Pageable pageable);
    Page<Res_CarDTO> filterCarByManufacture(String name, Pageable pageable);
    Page<Res_CarDTO> searchCarName(String name, Pageable pageable);

    Res_CarDTO createCar(Req_CarDTO newCar) throws Exception;

    Res_CarDTO updateCar(Req_CarDTO newCar) throws Exception;

    String removeCar(String id) throws Exception;
}
