package com.xcorp.springbootpractice.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@Validated
public class Car {
    @Id
    private String id = UUID.randomUUID().toString();

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Model model;

    @NotNull
    @ManyToOne
    private Manufacture manufactureId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
    private Date buyDate;

    public Car(String name, Model model, Manufacture manufactureId, Date buyDate) {
        this.name = name;
        this.model = model;
        this.manufactureId = manufactureId;
        this.buyDate = buyDate;
    }
}
