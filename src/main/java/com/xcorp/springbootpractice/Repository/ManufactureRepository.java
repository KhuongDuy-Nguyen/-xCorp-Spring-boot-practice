package com.xcorp.springbootpractice.Repository;

import com.xcorp.springbootpractice.Model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture, String> {
  Manufacture findByManufactureName(String name);

  Manufacture findByManufactureId(String id);
}
