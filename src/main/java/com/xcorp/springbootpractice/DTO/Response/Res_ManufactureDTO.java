package com.xcorp.springbootpractice.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//@RequiredArgsConstructor
public class Res_ManufactureDTO {

    private String manufactureId;

    private String manufactureName;

    private String manufactureAddress;

    public Res_ManufactureDTO(String manufactureName, String manufactureAddress) {
        this.manufactureName = manufactureName;
        this.manufactureAddress = manufactureAddress;
    }
}
