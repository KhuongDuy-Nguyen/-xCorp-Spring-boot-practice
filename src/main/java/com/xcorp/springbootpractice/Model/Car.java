package com.xcorp.springbootpractice.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Car{
    @Id
    private String carId = UUID.randomUUID().toString();

    @NotNull
    private String carName;

    @NotNull
    @ManyToOne
    private Model carModel;

    @NotNull
    @ManyToOne
    private Manufacture carManufacture;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
    private Date buyDate;

    public Car(String name, Model model, Manufacture manufacture, Date buyDate) {
        this.carName = name;
        this.carModel = model;
        this.carManufacture = manufacture;
        this.buyDate = buyDate;
    }
}
