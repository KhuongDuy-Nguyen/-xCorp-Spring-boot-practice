package com.xcorp.springbootpractice.Model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {

  @Id private String modelId = UUID.randomUUID().toString();

  @NotNull private String modelName;

  public Model(String name) {
    this.modelName = name;
  }
}
