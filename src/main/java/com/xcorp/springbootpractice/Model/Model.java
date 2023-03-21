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
@ToString
public class Model {

    @Id
    private String modelId = UUID.randomUUID().toString();

    @NotNull
    private String modelName;

    public Model(String name) {
        this.modelName = name;
    }
}
