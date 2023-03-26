package com.xcorp.springbootpractice.Repository;

import com.xcorp.springbootpractice.Model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, String> {
  Model findByModelName(String name);
}
