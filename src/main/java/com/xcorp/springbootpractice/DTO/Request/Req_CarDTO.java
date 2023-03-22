package com.xcorp.springbootpractice.DTO.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Req_CarDTO {
    private String carId;

    private String carName;

    private String carModelId;

    private String carManufactureId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
    private Date buyDate;

    public Req_CarDTO(String carName, String carModelId, String carManufactureId, Date buyDate) {
        this.carName = carName;
        this.carModelId = carModelId;
        this.carManufactureId = carManufactureId;
        this.buyDate = buyDate;
    }
}
