package com.leha.cars;


import com.leha.cars.core.CarHandler;
import com.leha.cars.core.CarStorage;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) {
        CarStorage carStorage = new CarStorage();
        new CarHandler(carStorage);
        if(args.length == 2){
            Type type;
            try {
                type = Type.valueOf(args[0]);
            }catch (Exception e){
                System.out.println("Invalid first argument (BRAND,MODEL,KILOMETRES,YEAR)");
                return;
            }
            try {
                carStorage.search(type,args[1]);
            } catch (IOException e) {}
        }
    }
}
