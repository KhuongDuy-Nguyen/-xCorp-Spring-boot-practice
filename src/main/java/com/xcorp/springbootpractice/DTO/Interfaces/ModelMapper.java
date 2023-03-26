package com.xcorp.springbootpractice.DTO.Interfaces;

import com.xcorp.springbootpractice.DTO.Response.ResModelDto;
import com.xcorp.springbootpractice.Model.Model;
import org.mapstruct.factory.Mappers;

public interface ModelMapper {
  ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

  ResModelDto modelToModelDto(Model model);
}
