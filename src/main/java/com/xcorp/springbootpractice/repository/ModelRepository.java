package com.xcorp.springbootpractice.repository;

import com.xcorp.springbootpractice.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, String> {
  Model findByModelName(String name);
}
