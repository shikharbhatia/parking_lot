package com.parkinglot.readers;

import com.parkinglot.constants.QueryConstants;
import com.parkinglot.delegators.InputDelegate;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReader {

    private InputDelegate delegate = new InputDelegate();

    public void read(BufferedReader reader) throws IOException {
        try {
            String str = reader.readLine();
            while (!(QueryConstants.EXIT).equalsIgnoreCase(str) && str != null) {
                String[] cmd = str.split(" ");
                delegate.processInput(cmd);
                str = reader.readLine();
            }
        } catch(IOException e){
            throw e;
        }
    }
}
