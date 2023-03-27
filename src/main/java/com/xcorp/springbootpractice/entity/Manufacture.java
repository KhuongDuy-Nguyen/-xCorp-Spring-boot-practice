package com.xcorp.springbootpractice.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Manufacture {
  @Id private String manufactureId = UUID.randomUUID().toString();

  @NotNull private String manufactureName;

  @NotNull private String manufactureAddress;

  public Manufacture(String name, String address) {
    this.manufactureName = name;
    this.manufactureAddress = address;
  }
}
