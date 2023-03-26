package com.xcorp.springbootpractice.DTO.Interfaces;

import com.xcorp.springbootpractice.DTO.Response.ResManufactureDto;
import com.xcorp.springbootpractice.Model.Manufacture;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel =
        MappingConstants.ComponentModel
            .SPRING) // Add Spring's application context to use @Autowired
public interface ManufactureMapper {
  ManufactureMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(ManufactureMapper.class);

  ResManufactureDto manufactureToManufactureDto(Manufacture manufacture);


}
