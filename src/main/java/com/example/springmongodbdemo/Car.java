package com.example.springmongodbdemo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Car")
public class Car {
    @Id
    int id;
    String title;
    String fuelType;
    long price;
    boolean isNew;
    long mileage;
    String firstRegistration;

    public Car(int id, String title, String fuelType, long price, boolean isNew, long mileage, String firstRegistration){
        this.id = id;
        this.title = title;
        this.fuelType = fuelType;
        this.price = price;
        this.isNew = isNew;
        this.mileage = mileage;
        this.firstRegistration = firstRegistration;
    }
}