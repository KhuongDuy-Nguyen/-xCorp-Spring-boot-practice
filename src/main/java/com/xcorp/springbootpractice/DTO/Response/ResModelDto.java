package com.xcorp.springbootpractice.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
// @RequiredArgsConstructor
public class ResModelDto {
  private String modelId;
  private String modelName;
}
