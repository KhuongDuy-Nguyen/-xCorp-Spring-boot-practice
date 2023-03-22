package com.xcorp.springbootpractice.DTO.Interfaces;

import com.xcorp.springbootpractice.DTO.Response.Res_ManufactureDTO;
import com.xcorp.springbootpractice.Model.Manufacture;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        // Add Spring's application context to use @Autowired
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ManufactureMapper {
    ManufactureMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(ManufactureMapper.class);

    Res_ManufactureDTO manufactureToManufactureDTO(Manufacture manufacture);
}
