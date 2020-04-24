package com.parkinglot.delegators;

import com.parkinglot.constants.MessageConstants;
import com.parkinglot.constants.QueryConstants;
import com.parkinglot.services.ParkingLotService;

public class InputDelegate {
    private ParkingLotService parkingLotService = new ParkingLotService();

    public void processInput(String[] queries) {
        switch (queries[0]) {
            case QueryConstants.CREATE_PARKING_LOT:
                System.out.println(parkingLotService.createParkinglot(Integer.parseInt(queries[1])));
                break;

            case QueryConstants.PARK:
                System.out.println(parkingLotService.park(queries[1], queries[2]));
                break;

            case QueryConstants.LEAVE:
                System.out.println(parkingLotService.leave(Integer.parseInt(queries[1])));
                break;

            case QueryConstants.STATUS:
                parkingLotService.findStatus();
                break;

            case QueryConstants.CAR_REGISTRATION_COLOUR:
                String queryResult = parkingLotService.findRegNumberForCarsWithColour(queries[1]);
                if (queryResult == null || queryResult.isEmpty())
                    System.out.println(MessageConstants.NOT_FOUND);
                else
                    System.out.println(queryResult);
                break;

            case QueryConstants.SLOT_NUMBER_CAR_REGISTRATION:
                String slotNumber = parkingLotService.findSlotNumberForRegistrationNumber(queries[1]);
                if (slotNumber == null || slotNumber.isEmpty() ) {
                    System.out.println(MessageConstants.NOT_FOUND);
                } else
                    System.out.println(slotNumber);
                break;

            case QueryConstants.SLOT_NUMBER_CAR_COLOUR:
                System.out.println(parkingLotService.findSlotNumbersForCarsWithColour(queries[1]));
                break;

            case QueryConstants.EXIT:
                return;

            default:
                System.out.println(MessageConstants.INVALID_COMMAND);
        }
    }
}
