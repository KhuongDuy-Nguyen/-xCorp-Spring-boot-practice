package com.xcorp.springbootpractice.controller;

import com.xcorp.springbootpractice.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/model")
public class ModelController {

  @Autowired private ModelRepository modelRepository;

  @GetMapping()
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(modelRepository.findAll());
  }
}
