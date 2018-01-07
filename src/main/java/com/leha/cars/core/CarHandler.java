package com.leha.cars.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leha.cars.model.Car;

import java.io.File;
import java.io.IOException;

public class CarHandler implements Runnable {

    private static final String PATH = "loader";
    private File dir = new File(PATH);
    private CarStorage carStorage;
    private ObjectMapper objectMapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();
    private Thread thread;

    public CarHandler(CarStorage carStorage) {
        this.carStorage = carStorage;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        File files[] = dir.listFiles();
        int len = files.length;
        if(len == 0){
            return;
        }
        for (File file : files){
            Car car;
            try {
                car = objectMapper.readValue(file,new TypeReference<Car>(){});
            } catch (IOException e) {
                try {
                    car = xmlMapper.readValue(file,new TypeReference<Car>(){});
                } catch (IOException e1) {
                    continue;
                }
            }
            try {
                carStorage.addCar(car);
            } catch (IOException e) {}
            file.delete();
        }
    }
}
