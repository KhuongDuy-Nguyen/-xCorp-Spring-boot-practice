package com.xcorp.springbootpractice.Controller;

import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Model.Page;
import com.xcorp.springbootpractice.Service.ManufactureService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manufacture")
public class ManufactureController {
    @Autowired
    ManufactureService manufactureService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestBody Page page){
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getSize());
       return ResponseEntity.ok(manufactureService.getAllManufactures(pageable));
    }

    @PostMapping()
    public ResponseEntity<?> createManufacture(@RequestBody Manufacture newManufacture){
        return ResponseEntity.ok(manufactureService.createManufacture(newManufacture));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updateManufacture(@RequestBody Manufacture newManufacture, @PathVariable String id) throws Exception {
        return ResponseEntity.ok(manufactureService.updateManufacture(newManufacture, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteManufacture(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(manufactureService.removeManufacture(id));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
