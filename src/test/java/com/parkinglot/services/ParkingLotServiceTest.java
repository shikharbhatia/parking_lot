package com.parkinglot.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingLotServiceTest {
    private ParkingLotService parkingLotService;

    @Before
    public void setUp() {
        parkingLotService = new ParkingLotService();
    }
    @Test
    public void itShouldCreateParkingLot(){
        String actual = parkingLotService.createParkinglot(102);
        String expected = "Created a parking lot with 102 slots";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldNotCreateParkingLotIfAlreadyCreated(){
        parkingLotService.createParkinglot(4);
        String actual = parkingLotService.createParkinglot(102);
        String expected = "Parking Lot already exists";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldTestParkCase() {
        String regNumber = "MH 03 DA 0892";
        String color = "Oxford Blue";
        parkingLotService.createParkinglot(5);
        String actual = parkingLotService.park(regNumber, color);
        String expected = "Allocated slot number: 1";
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void itShouldTestParkCaseWhenNoParkingLot() {
        String regNumber = "MH 03 DA 0892";
        String color = "Oxford Blue";
        String actual = parkingLotService.park(regNumber, color);
        String expected = "Parking Lot not created";
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void itShouldTestParkingFullScenario() {
        parkingLotService.createParkinglot(2);
        parkingLotService.park("MH 03 DA 0892", "Oxford Blue");
        parkingLotService.park("MH 03 BJ 4342", "Glisening Gray");
        String actual = parkingLotService.park("MH 03 BJ 1000", "Blood Red");
        String expected = "Sorry, parking lot is full";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldTestLeaveCase() {
        parkingLotService.createParkinglot(5);
        parkingLotService.park("MH 03 DA 0892", "Oxford Blue");
        String actual = parkingLotService.leave(1);
        String expected = "Slot number 1 is free";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldTestIncorrectSlotRequest() {
        parkingLotService.createParkinglot(5);
        parkingLotService.park("MH 03 DA 0892", "Oxford Blue");
        String actual = parkingLotService.leave(2);
        String expected = "Incorrect Slot number requested";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldTestStatusOperation() {
        parkingLotService.createParkinglot(2);
        parkingLotService.park("MH 03 DA 0892", "Oxford Blue");
        parkingLotService.park("MH 03 BJ 4342", "Glisening Gray");
        parkingLotService.findStatus();
    }

    @Test
    public void itShouldTestStatusOperationWithoutParkingLotCreation() {
        parkingLotService.findStatus();
    }

    @Test
    public void itShouldGiveSlotNumberGivenRegNumber() {
        parkingLotService.createParkinglot(5);
        parkingLotService.park("MH 03 DA 0892", "Oxford Blue");
        parkingLotService.park("MH 03 BJ 4342", "Glisening Gray");
        String actual = parkingLotService.findSlotNumberForRegistrationNumber("MH 03 BJ 4342");
        String expected = "2";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldPrintErrorIfParkingSlotNotCreated() {
        String actual = parkingLotService.findSlotNumberForRegistrationNumber("MH 03 BJ 4342");
        String expected = "Parking Lot not created";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldGiveSlotNumbersGivenColor() {
        parkingLotService.createParkinglot(5);
        parkingLotService.park("MH 03 DA 0892", "Oxford Blue");
        parkingLotService.park("MH 03 DA 0890", "Oxford Blue");
        parkingLotService.park("MH 03 BJ 4342", "Glisening Gray");
        String actual = parkingLotService.findSlotNumbersForCarsWithColour("Oxford Blue");
        String expected = "1, 2";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldPrintErrorIfParkinglotNotCreated_SlotNumberGivenColor() {
        String actual = parkingLotService.findSlotNumbersForCarsWithColour("Blue");
        String expected = "Parking Lot not created";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldGiveRegNumberGivenColor(){
        parkingLotService.createParkinglot(5);
        parkingLotService.park("MH 03 DA 0892", "Oxford Blue");
        parkingLotService.park("MH 03 DA 0890", "Oxford Blue");
        parkingLotService.park("MH 03 BJ 4342", "Glisening Gray");
        String actual = parkingLotService.findRegNumberForCarsWithColour("Oxford Blue");
        String expected = "MH 03 DA 0892, MH 03 DA 0890";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itShouldPrintErrorIfParkinglotNotCreated_RegNumberGivenColor() {
        String actual = parkingLotService.findRegNumberForCarsWithColour("Blue");
        String expected = "Parking Lot not created";
        Assert.assertEquals(expected, actual);
    }
}
