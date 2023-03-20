package com.xcorp.springbootpractice.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotNull
    private String name;

    public Model(String name) {
        this.name = name;
    }
}
