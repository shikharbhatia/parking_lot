package com.parkinglot;

import com.parkinglot.exceptions.ParkingLotException;
import com.parkinglot.readers.InputReader;

import java.io.*;

public class ParkingLotApplication {
    public static void main(String[] args) throws ParkingLotException {
        try {
            InputReader reader = new InputReader();
            if (args.length > 0) {
                reader.read(new BufferedReader(new FileReader(new File(args[0]))));
            } else {
                reader.read(new BufferedReader(new InputStreamReader(System.in)));
            }
        } catch (IOException e){
            throw new ParkingLotException(e.getMessage()) ;
        }
    }
}
