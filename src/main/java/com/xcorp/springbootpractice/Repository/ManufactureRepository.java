package com.xcorp.springbootpractice.Repository;

import com.xcorp.springbootpractice.Model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture, String> {
    Manufacture findAllByName(String name);

    Manufacture findManufactureById(String id);
    Manufacture getManufactureById(String id);
    Manufacture findManufactureByName(String name);

    void deleteById(String id);


}