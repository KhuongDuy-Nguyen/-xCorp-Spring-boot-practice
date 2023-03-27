package com.xcorp.springbootpractice.dto.interfaces;

import com.xcorp.springbootpractice.dto.response.ResManufactureDto;
import com.xcorp.springbootpractice.entity.Manufacture;
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
