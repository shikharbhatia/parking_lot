package com.parkinglot.services;

import com.parkinglot.constants.MessageConstants;
import com.parkinglot.models.Car;
import com.parkinglot.models.ParkingLot;
import com.parkinglot.models.Slot;
import com.parkinglot.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotService {

    private ParkingLot parkingLot = null;

    public String createParkinglot(Integer parkingLotSize) {
        if (parkingLot != null) {
           return MessageConstants.PARKING_LOT_EXISTS;
        }
        parkingLot = ParkingLot.PARKING_LOT_INSTANCE;
        List<Slot> slots = new ArrayList<>();
        for(int i = 1; i <= parkingLotSize; i++){
            Slot slot = new Slot(i);
            slots.add(slot);
        }
        parkingLot.setSlots(slots);
        return MessageConstants.PARKING_LOT_CREATED.replaceFirst("%", parkingLotSize.toString());
    }

    public String park(String registrationNumber, String color) {
        if(parkingLot == null)
            return MessageConstants.PARKING_LOT_NOT_CREATED;
        Slot availableSlot = nearestAvailableSlot();
        if (availableSlot == null) {
            return MessageConstants.PARKING_FULL;
        }
        Vehicle car = new Car(registrationNumber, color);
        availableSlot.setVehicle(car);
        availableSlot.setSlotAvailable(false);

        return MessageConstants.ALLOCATED_SLOT + availableSlot.getSlotNumber();
    }

    public String leave(Integer slotNumber) {
        if (parkingLot != null && parkingLot.getSlots() != null && slotNumber <= parkingLot.getSlots().size() && !parkingLot.getSlots().get(slotNumber-1).isSlotAvailable()) {
            Slot slot = parkingLot.getSlots().get(slotNumber-1);
            slot.setVehicle(null);
            slot.setSlotAvailable(true);
            return MessageConstants.SLOT_FREE.replaceFirst("%", slotNumber.toString());
        } else
            return MessageConstants.INCORRECT_SLOT;
    }

    public void findStatus() {
        if (parkingLot == null)
            return;

        List<Slot> slots = parkingLot.getSlots();

        System.out.println("Slot No.    Registration No    Colour");

        slots.stream()
                .filter(s -> !s.isSlotAvailable())
                .forEach(slot -> System.out.format("%d           %s      %s%n",
                        slot.getSlotNumber(), slot.getVehicle().getRegistrationNumber(), slot.getVehicle().getColor()));
    }

    public String findRegNumberForCarsWithColour(String color) {
        if (parkingLot == null)
            return MessageConstants.PARKING_LOT_NOT_CREATED;
        List<Slot> slots = parkingLot.getSlots();

        String regNumberOfCarsWithColor = slots.stream()
                .filter(s -> !s.isSlotAvailable() && color.equalsIgnoreCase(s.getVehicle().getColor()))
                .map(slot -> slot.getVehicle().getRegistrationNumber())
                .collect(Collectors.joining(", "));

        return regNumberOfCarsWithColor;
    }

    public String findSlotNumberForRegistrationNumber(String registrationNumber) {
        if(parkingLot == null)
            return MessageConstants.PARKING_LOT_NOT_CREATED;
        List<Slot> slots = parkingLot.getSlots();

        String slotNumber = slots.stream()
                .filter(s -> !s.isSlotAvailable()
                        && registrationNumber.equalsIgnoreCase(s.getVehicle().getRegistrationNumber()))
                .map(slot -> String.valueOf(slot.getSlotNumber()))
                .collect(Collectors.joining());
        return slotNumber;
    }

    public String findSlotNumbersForCarsWithColour(String color) {
        if (parkingLot == null)
            return MessageConstants.PARKING_LOT_NOT_CREATED;
        List<Slot> slots = parkingLot.getSlots();

        String regNumberOfCarsWithColor = slots.stream()
                .filter(s -> !s.isSlotAvailable() && color.equalsIgnoreCase(s.getVehicle().getColor()))
                .map(slot -> String.valueOf(slot.getSlotNumber()))
                .collect(Collectors.joining(", "));

        return regNumberOfCarsWithColor;
    }

    private Slot nearestAvailableSlot() {
        return parkingLot.getSlots().stream()
                .filter(s -> s.isSlotAvailable())
                .findFirst()
                .orElse(null);
    }
}
