package com.xcorp.springbootpractice.Controller;

import com.xcorp.springbootpractice.Model.Car;
import com.xcorp.springbootpractice.Service.CarService;
import com.xcorp.springbootpractice.Service.Impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(carService.getAllCars());
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
