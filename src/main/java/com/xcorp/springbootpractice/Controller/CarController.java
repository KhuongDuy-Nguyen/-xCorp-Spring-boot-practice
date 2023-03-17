package com.xcorp.springbootpractice.Controller;

import com.xcorp.springbootpractice.Model.Car;
import com.xcorp.springbootpractice.Model.Page;
import com.xcorp.springbootpractice.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestBody Page page) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getSize());
        return ResponseEntity.ok(carService.getAllCars(pageable));
    }

    @GetMapping("/desc")
    public ResponseEntity<?> getAllByDesc(@RequestBody Page page) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getSize(), Sort.by(page.getSort()).descending());
        return ResponseEntity.ok(carService.getAllCars(pageable));
    }

    @GetMapping("/asc")
    public ResponseEntity<?> getAllByAsc(@RequestBody Page page) {
        Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getSize(), Sort.by(page.getSort()).ascending());
        return ResponseEntity.ok(carService.getAllCars(pageable));
    }

    @PostMapping()
    public ResponseEntity<?> createCar(@RequestBody Car newCar) throws Exception {
        return ResponseEntity.ok(carService.createCar(newCar));
    }

    @PutMapping()
    public ResponseEntity<?> updateCar(@RequestBody Car newCar) throws Exception {
        return ResponseEntity.ok(carService.updateCar(newCar));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteCar(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(carService.removeCar(id));
    }

//    Hander Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
