package com.xcorp.springbootpractice.dto.interfaces;

import com.xcorp.springbootpractice.dto.response.ResCarDto;
import com.xcorp.springbootpractice.entity.Car;
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
