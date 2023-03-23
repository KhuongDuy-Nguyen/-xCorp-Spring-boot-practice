package com.xcorp.springbootpractice.Controller;

import com.xcorp.springbootpractice.DTO.Request.Req_ManufactureDTO;
import com.xcorp.springbootpractice.Service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/manufacture")
public class ManufactureController {
    @Autowired
    ManufactureService manufactureService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam("page") int page,
                                    @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page, size);
       return ResponseEntity.ok(manufactureService.getAllManufactures(pageable));
    }

    @PostMapping()
    public ResponseEntity<?> createManufacture(@RequestBody @Valid Req_ManufactureDTO newManufacture){
        return ResponseEntity.ok(manufactureService.createManufacture(newManufacture));
    }

    @PutMapping()
    public ResponseEntity<?>  updateManufacture(@RequestBody @Valid Req_ManufactureDTO newManufacture) throws Exception {
        return ResponseEntity.ok(manufactureService.updateManufacture(newManufacture));
    }

    @DeleteMapping()
    public ResponseEntity<?>  deleteManufacture(@RequestParam String id) throws Exception {
        return ResponseEntity.ok(manufactureService.removeManufacture(id));
    }
}
