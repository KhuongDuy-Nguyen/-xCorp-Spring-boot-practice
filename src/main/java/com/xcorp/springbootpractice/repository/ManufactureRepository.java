package com.xcorp.springbootpractice.repository;

import com.xcorp.springbootpractice.entity.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture, String> {
  Manufacture findByManufactureName(String name);

  Manufacture findByManufactureId(String id);
}
