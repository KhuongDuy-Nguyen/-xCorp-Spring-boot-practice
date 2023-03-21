package com.xcorp.springbootpractice.Model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@ToString
public class Manufacture {
    @Id
    private String manufactureId = UUID.randomUUID().toString();

    @NotNull
    private String manufactureName;

    @NotNull
    private String manufactureAddress;

    public Manufacture(String name, String address) {
        this.manufactureName = name;
        this.manufactureAddress = address;
    }
}
