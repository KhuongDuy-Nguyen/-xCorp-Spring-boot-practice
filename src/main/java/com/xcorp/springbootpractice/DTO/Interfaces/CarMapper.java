package com.xcorp.springbootpractice.DTO.Interfaces;

import com.xcorp.springbootpractice.DTO.Response.ResCarDto;
import com.xcorp.springbootpractice.Model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel =
        MappingConstants.ComponentModel
            .SPRING) // Add Spring's application context to use @Autowired
public interface CarMapper {
  CarMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(CarMapper.class);

  @Mapping(target = "carModel", source = "carModel.modelName")
  @Mapping(target = "carManufacture", source = "carManufacture.manufactureName")
  ResCarDto carToCarDto(Car car);
}
