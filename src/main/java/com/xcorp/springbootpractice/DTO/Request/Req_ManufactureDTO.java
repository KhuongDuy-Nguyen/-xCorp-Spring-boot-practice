package com.xcorp.springbootpractice.DTO.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Req_ManufactureDTO {
    private String manufactureId;

    private String manufactureName;

    private String manufactureAddress;
}
