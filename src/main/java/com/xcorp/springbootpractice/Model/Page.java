package com.xcorp.springbootpractice.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private int pageNumber;
    private int size;
    private String sort;

    public Page(int pageNumber, int size) {
        this.pageNumber = pageNumber;
        this.size = size;
    }
}
