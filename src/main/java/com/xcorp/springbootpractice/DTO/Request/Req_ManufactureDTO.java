package com.xcorp.springbootpractice.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Validated
public class Req_ManufactureDTO {

    @NotBlank(message = "manufactureId is required")
    private String manufactureId;

    @NotBlank(message = "manufactureName is required")
    private String manufactureName;

    @NotBlank(message = "manufactureAddress is required")
    private String manufactureAddress;
}
