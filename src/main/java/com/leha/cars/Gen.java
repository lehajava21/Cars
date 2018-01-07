package com.leha.cars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leha.cars.model.Car;

import java.io.File;
import java.io.IOException;

public class Gen {
    private static final String JSON = "container\\car.json";
    private static final String XML = "container\\car.xml";

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();
        Car car = new Car(
                "Toyota",
                "qqq",
                2000,
                1000);
        objectMapper.writeValue(new File(JSON), car);
        xmlMapper.writeValue(new File(XML), car);
    }
}
