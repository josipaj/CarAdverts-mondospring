package com.example.springmongodbdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Sort;

@RestController

public class CarController {

    private CarRepository carRepo;

    @Autowired
    public CarController(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    List<String> list = Arrays.asList("Id must be a positive number", "Price cannot be negative");

    @PostMapping("/car/adverts")
    public ResponseEntity<Object> addCar(@RequestBody Car car){
        int id = car.id;
        long price = car.price;
        try {
            if (id >= 0 && price > 0)
                return ResponseEntity.status(HttpStatus.CREATED).body(carRepo.save(car));
            else
                return ResponseHandler.generateResponse(list, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/car/adverts/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        var car = carRepo.findById(id);
        if (!car.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(carRepo.findById(id),HttpStatus.OK);      
    }

    @GetMapping("/car/adverts")
    public List<Car> getAll(@RequestParam(required = false, name = "sortby") String key){
        if(key != null) {            
            Sort sort = Sort.by(Sort.Direction.ASC, key);
            List<Car> data = carRepo.findAll(sort);
            return data;
        } else{
            Sort sort = Sort.by(Sort.Direction.ASC, "id");
            return carRepo.findAll(sort);
        }
    }

    @PutMapping("/car/adverts/{id}")
    public ResponseEntity<Object> modifyCar(@RequestBody Car car){
        int id = car.id;
        long price = car.price;
        try {
            if (id >= 0 && price > 0)
                return ResponseEntity.status(HttpStatus.CREATED).body(carRepo.save(car));
            return ResponseHandler.generateResponse(list, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/car/adverts/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") int id) {
        var car = carRepo.findById(id);
        if (!car.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        carRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("None");   
    }

}
  