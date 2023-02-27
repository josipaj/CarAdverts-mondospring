package com.example.springmongodbdemo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car,Integer> {

}