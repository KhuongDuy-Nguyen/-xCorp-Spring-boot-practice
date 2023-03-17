package com.xcorp.springbootpractice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

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
    private String model;

    @NotNull
    @ManyToOne
    private Manufacture manufactureId;

    @NotNull
    private Date buyDate = new Date();

    public Car(String name, String model, Manufacture manufactureId, Date buyDate) {
        this.name = name;
        this.model = model;
        this.manufactureId = manufactureId;
        this.buyDate = buyDate;
    }
}
