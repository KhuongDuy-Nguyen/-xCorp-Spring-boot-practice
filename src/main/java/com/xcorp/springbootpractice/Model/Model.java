package com.xcorp.springbootpractice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model{

    @Id
    private String modelId = UUID.randomUUID().toString();

    @NotNull
    private String modelName;

    public Model(String name) {
        this.modelName = name;
    }
}
