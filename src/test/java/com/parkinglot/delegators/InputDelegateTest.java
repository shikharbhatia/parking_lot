package com.parkinglot.delegators;

import com.parkinglot.constants.QueryConstants;
import com.parkinglot.services.ParkingLotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InputDelegateTest {
    @Mock
    private ParkingLotService parkingLotService;

    @InjectMocks
    private InputDelegate inputDelegate;

    @Test
    public void itShouldCallCreateParkingLotCase() {
       String [] commands = new String[3];
       commands[0] = QueryConstants.CREATE_PARKING_LOT;
       commands[1] = "5";
       when(parkingLotService.createParkinglot(anyInt())).thenReturn("testString");
       inputDelegate.processInput(commands);
       Mockito.verify(parkingLotService, times(1)).createParkinglot(anyInt());
    }

    @Test
    public void itShouldCallParkCase() {
        String [] commands = new String[3];
        commands[0] = QueryConstants.PARK;
        commands[1] = "anyString";
        when(parkingLotService.park(anyString(), anyString())).thenReturn("testString");
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).park(anyString(), anyString());

    }

    @Test
    public void itShouldCallLeaveCase() {
        String [] commands = new String[3];
        commands[0] = QueryConstants.LEAVE;
        commands[1] = "5";
        when(parkingLotService.leave(anyInt())).thenReturn("testString");
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).leave(anyInt());
    }

    @Test
    public void itShouldCallStatusCase() {
        String [] commands = new String[1];
        commands[0] = QueryConstants.STATUS;
        doNothing().when(parkingLotService).findStatus();
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).findStatus();
    }

    @Test
    public void itShouldCallCarRegistrationWithColorCase() {
        String [] commands = new String[2];
        commands[0] = QueryConstants.CAR_REGISTRATION_COLOUR;
        when(parkingLotService.findRegNumberForCarsWithColour(anyString())).thenReturn("anyString");
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).findRegNumberForCarsWithColour(anyString());
    }
    @Test
    public void itShouldPrintNotFoundCarRegistrationWithColorCase() {
        String [] commands = new String[2];
        commands[0] = QueryConstants.CAR_REGISTRATION_COLOUR;
        when(parkingLotService.findRegNumberForCarsWithColour(anyString())).thenReturn(null);
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).findRegNumberForCarsWithColour(anyString());
    }

    @Test
    public void itShouldCallSlotNumberWithCarRegCase() {
        String [] commands = new String[2];
        commands[0] = QueryConstants.SLOT_NUMBER_CAR_REGISTRATION;
        when(parkingLotService.findSlotNumberForRegistrationNumber(anyString())).thenReturn("anyString");
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).findSlotNumberForRegistrationNumber(anyString());
    }
    @Test
    public void itShouldPrintNotFoundSlotNumberWithCarRegCase() {
        String [] commands = new String[2];
        commands[0] = QueryConstants.SLOT_NUMBER_CAR_REGISTRATION;
        when(parkingLotService.findSlotNumberForRegistrationNumber(anyString())).thenReturn(null);
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).findSlotNumberForRegistrationNumber(anyString());
    }

    @Test
    public void itShouldCallSlotNumberWithCarColorCase() {
        String [] commands = new String[2];
        commands[0] = QueryConstants.SLOT_NUMBER_CAR_COLOUR;
        when(parkingLotService.findSlotNumbersForCarsWithColour(anyString())).thenReturn("anyString");
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).findSlotNumbersForCarsWithColour(anyString());
    }

    @Test
    public void itShouldPrintNotFoundSlotNumberWithCarColorCase() {
        String [] commands = new String[2];
        commands[0] = QueryConstants.SLOT_NUMBER_CAR_COLOUR;
        when(parkingLotService.findSlotNumbersForCarsWithColour(anyString())).thenReturn(null);
        inputDelegate.processInput(commands);
        Mockito.verify(parkingLotService, times(1)).findSlotNumbersForCarsWithColour(anyString());
    }
    @Test
    public void itShouldCallExitCase() {
        String [] commands = new String[2];
        commands[0] = QueryConstants.EXIT;
        inputDelegate.processInput(commands);
    }

    @Test
    public void itShouldCallDefaultCase() {
        String [] commands = new String[2];
        commands[0] = "randomString";
        inputDelegate.processInput(commands);
    }

}
