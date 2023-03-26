package com.xcorp.springbootpractice.DTO.Request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Validated
public class ReqManufactureDto {
  @NotBlank(message = "manufactureId is required")
  private String manufactureId;

  @NotBlank(message = "manufactureName is required")
  private String manufactureName;

  @NotBlank(message = "manufactureAddress is required")
  private String manufactureAddress;
}
