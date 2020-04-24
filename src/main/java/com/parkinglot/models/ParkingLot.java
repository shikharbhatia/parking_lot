package com.parkinglot.models;

import java.util.List;

public enum ParkingLot {
    PARKING_LOT_INSTANCE;

    private List<Slot> slots;

    public List<Slot> getSlots() {
        return slots;
    }
    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
