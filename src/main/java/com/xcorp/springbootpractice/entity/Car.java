package com.xcorp.springbootpractice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// @Validated
public class Car {
  @Id private String carId = UUID.randomUUID().toString();

  private String carName;

  @ManyToOne private Model carModel;

  @ManyToOne private Manufacture carManufacture;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
  private Date buyDate;

  public Car(String name, Model model, Manufacture manufacture, Date buyDate) {
    this.carName = name;
    this.carModel = model;
    this.carManufacture = manufacture;
    this.buyDate = buyDate;
  }
}
