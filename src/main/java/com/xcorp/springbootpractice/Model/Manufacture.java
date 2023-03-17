package com.xcorp.springbootpractice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Manufacture {
    @Id
    private String id = UUID.randomUUID().toString();

    @NotNull
    private String name;

    @NotNull
    private String address;

    public Manufacture(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
