package com.xcorp.springbootpractice.DTO.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ReqCarDto {
  //    @NotBlank(message = "carId is required")
  private String carId;

  @NotBlank(message = "carName is required")
  private String carName;

  @NotBlank(message = "carModelId is required")
  private String carModelId;

  @NotBlank(message = "carManufactureId is required")
  private String carManufactureId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
  private Date buyDate;
}
