package com.parkinglot.readers;

import com.parkinglot.delegators.InputDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InputReaderTest {
    @Mock
    private InputDelegate inputDelegate;

    @InjectMocks
    private InputReader inputReaderUnderTest;

    @Test
    public void itShouldTestRead() throws Exception {
        final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/inputFiles/file_input.txt")));
        doNothing().when(inputDelegate).processInput(any(String [].class));
        inputReaderUnderTest.read(reader);

        verify(inputDelegate).processInput(any(String[].class));
    }

    @Test(expected = IOException.class)
    public void itShouldThrowParkingLotException() throws Exception {
        final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/inputFiles/file_input.txt")));
        doThrow(IOException.class).when(inputDelegate).processInput(any(String [].class));
        inputReaderUnderTest.read(reader);
    }
}
