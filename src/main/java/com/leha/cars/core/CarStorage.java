package com.leha.cars.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leha.cars.Type;
import com.leha.cars.model.Car;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CarStorage {

    private static final String PATH = "storage.json";
    private static File storage = new File(PATH);
    private static List<Car> cars = Collections.synchronizedList(new ArrayList());
    private static ObjectMapper objectMapper = new ObjectMapper();

    public void addCar(Car car) throws IOException {
        if(car == null){
            return;
        }
        loadFromStorage();
        cars.add(car);
        objectMapper.writeValue(storage, cars);
    }

    public void search(Type type,String val) throws IOException {
        List<Car> res = null;
        if(!storage.exists()){
            storage.createNewFile();
        }
        loadFromStorage();
        switch (type){
            case BRAND:
                res = cars.stream()
                        .filter(c->val.equals(c.getBrand())).collect(Collectors.toList());
                break;
            case MODEL:
                res = cars.stream()
                        .filter(c->val.equals(c.getModel())).collect(Collectors.toList());
                break;
            case KILOMETERS:
                int kilo = Integer.parseInt(val);
                res = cars.stream()
                        .filter(c->kilo > (c.getKilometersRun())).collect(Collectors.toList());
                break;
            case YEAR:
                int year = Integer.parseInt(val);
                res = cars.stream()
                        .filter(c->year < (c.getYear())).collect(Collectors.toList());
        }
        if(res != null){
            res.forEach(c->System.out.println(c));
        }
    }

    private void loadFromStorage() throws IOException {
        if(storage.length() == 0){
            return;
        }
        cars = objectMapper.readValue(storage,new TypeReference<List<Car>>(){});
    }
}
