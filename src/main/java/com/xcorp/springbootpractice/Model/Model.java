package com.xcorp.springbootpractice.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
