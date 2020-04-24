package com.parkinglot.models;

public class Slot {
    private Integer slotNumber;
    private Vehicle vehicle;
    private boolean isSlotAvailable = true;

    public Slot(Integer slotNumber, Vehicle vehicle) {
        this.slotNumber = slotNumber;
        this.vehicle = vehicle;
    }
    public Slot(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isSlotAvailable() {
        return isSlotAvailable;
    }

    public void setSlotAvailable(boolean slotAvailable) {
        isSlotAvailable = slotAvailable;
    }
}
