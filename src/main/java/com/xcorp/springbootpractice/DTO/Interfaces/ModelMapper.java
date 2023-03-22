package com.xcorp.springbootpractice.DTO.Interfaces;

import com.xcorp.springbootpractice.DTO.Response.Res_ModelDTO;
import com.xcorp.springbootpractice.Model.Model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
    Res_ModelDTO modelToModelDTO(Model model);
}
