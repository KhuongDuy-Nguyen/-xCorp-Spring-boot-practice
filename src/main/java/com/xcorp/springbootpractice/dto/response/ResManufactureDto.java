package com.xcorp.springbootpractice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
// @RequiredArgsConstructor
public class ResManufactureDto {

  private String manufactureId;

  private String manufactureName;

  private String manufactureAddress;

  public ResManufactureDto(String manufactureName, String manufactureAddress) {
    this.manufactureName = manufactureName;
    this.manufactureAddress = manufactureAddress;
  }
}
