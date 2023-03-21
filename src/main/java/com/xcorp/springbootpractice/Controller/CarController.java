package com.xcorp.springbootpractice.Controller;

import com.xcorp.springbootpractice.Model.Car;
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
    public ResponseEntity<?> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(carService.getAllCars(pageable));
    }

    @GetMapping("/desc")
    public ResponseEntity<?> getAllByDesc(@RequestParam("page") int page,
                                          @RequestParam("size") int size,
                                          @RequestParam("sort") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return ResponseEntity.ok(carService.getAllCars(pageable));
    }

    @GetMapping("/asc")
    public ResponseEntity<?> getAllByAsc(@RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam("sort") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return ResponseEntity.ok(carService.getAllCars(pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterCarByManufacture(@RequestParam("name") String name,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(carService.filterCarByManufacture(name, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCarName(@RequestParam("name") String name,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(carService.searchCarName(name, pageable));
    }

    @PostMapping()
    public ResponseEntity<?> createCar(@RequestBody Car newCar) throws Exception {
        return ResponseEntity.ok(carService.createCar(newCar));
    }

    @PutMapping()
    public ResponseEntity<?> updateCar(@RequestBody Car newCar) throws Exception {
        return ResponseEntity.ok(carService.updateCar(newCar));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCar(@RequestParam String id) throws Exception {
        return ResponseEntity.ok(carService.removeCar(id));
    }

//    Hander Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
