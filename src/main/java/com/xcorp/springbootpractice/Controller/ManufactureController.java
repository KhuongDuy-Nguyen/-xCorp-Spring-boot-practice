package com.xcorp.springbootpractice.Controller;

import com.xcorp.springbootpractice.Model.Manufacture;
import com.xcorp.springbootpractice.Service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manufacture")
public class ManufactureController {
    @Autowired
    ManufactureService manufactureService;

    @GetMapping()
    public void getAll(){
        manufactureService.getAllManufactures();
    }

    @PostMapping()
    public ResponseEntity<?> createManufacture(@RequestBody Manufacture newManufacture){
        return ResponseEntity.ok(manufactureService.createManufacture(newManufacture));
    }

    @PutMapping()
    public ResponseEntity<?>  updateManufacture(@RequestBody Manufacture newManufacture) throws Exception {
        return ResponseEntity.ok(manufactureService.updateManufacture(newManufacture));
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
