package com.xcorp.springbootpractice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Validated
public class ReqModelDto {

  //    @NotBlank(message = "modelId is required")
  private String modelId;

  //    @NotBlank(message = "modelName is required")
  private String modelName;
}
