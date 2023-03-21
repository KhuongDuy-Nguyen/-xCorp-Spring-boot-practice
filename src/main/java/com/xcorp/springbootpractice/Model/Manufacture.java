package com.xcorp.springbootpractice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
