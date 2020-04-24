# PARKING LOT 
This is Simple Java Application written in Java 8, junit testing framework and maven build tool.

The application accepts inputs from both command line and interactive shell.

# Setup:
You can run the application by importing it in any ide like eclipse and intellij
 OR
by building it's jar using ``` mvn clean install ``` and then using the ``` java -jar jarName.jar ``` command.

# Assumptions:
1)User first will give create Slot command and then will issue other commands.
Example:
```
park KA-01-HH-1234 White
Parking Slot is yet to be created !
```

2)No two cars with same Registration number can be present at the same time.

3)If a user will enter some random command then some meaningful message will be displayed.
Example:
```
Random Example.
Please give a valid input !
```

4)Once a car leave parking Slot, it's data will not be persisted.
Example:
```
create_parking_lot 2
park KA-01-HH-1234 White
park KA-01-HH-9999 White
leave 2
slot_number_for_registration_number KA-01-HH-3141
Not Found
```

# Running Example :

Input:
create_parking_lot 6
park KA-01-HH-1234 White
park KA-01-HH-9999 White
park KA-01-BB-0001 Black
park KA-01-HH-7777 Red
park KA-01-HH-2701 Blue
park KA-01-HH-3141 Black
leave 4
status
park KA-01-P-333 White
park DL-12-AA-9999 White
registration_numbers_for_cars_with_colour White
slot_numbers_for_cars_with_colour White
slot_number_for_registration_number KA-01-HH-3141
slot_number_for_registration_number MH-04-AY-1111

Output:
Created a parking lot with 6 slots
Allocated slot number: 1
Allocated slot number: 2
Allocated slot number: 3
Allocated slot number: 4
Allocated slot number: 5
Allocated slot number: 6
Slot number 4 is free
Slot No.    Registration No     Colour
1         KA-01-HH-1234     White
2         KA-01-HH-9999     White
3         KA-01-BB-0001     Black
5         KA-01-HH-2701      Blue
6         KA-01-HH-3141     Black
Allocated slot number: 4
Sorry, parking lot is full
KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333
1, 2, 4
6
Not found
