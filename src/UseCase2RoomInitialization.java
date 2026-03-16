/**
 * Abstract Room class representing common properties
 * shared by all room types in the system.
 *
 * @author Priya
 * @version 2.0
 */
abstract class Room {

    protected String roomType;
    protected int numberOfBeds;
    protected double roomSize;
    protected double pricePerNight;

    public Room(String roomType, int numberOfBeds, double roomSize, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.roomSize = roomSize;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Room Size: " + roomSize + " sq ft");
        System.out.println("Price per Night: $" + pricePerNight);
    }
}
/**
 * Concrete class representing a Single Room.
 *
 * @version 2.0
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 80);
    }
}
/**
 * Concrete class representing a Double Room.
 *
 * @version 2.0
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 120);
    }
}
/**
 * Concrete class representing a Suite Room.
 *
 * @version 2.0
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 600, 250);
    }
}
/**
 * Book My Stay - Hotel Booking System
 *
 * Demonstrates initialization of different room types
 * using inheritance and abstraction.
 *
 * @author Priya
 * @version 2.1
 */
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 2.1");
        System.out.println(" Room Availability Overview");
        System.out.println("=================================\n");

        // Create room objects (polymorphism)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleRoomAvailable = 10;
        int doubleRoomAvailable = 6;
        int suiteRoomAvailable = 3;

        System.out.println("---- Single Room ----");
        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleRoomAvailable);

        System.out.println("\n---- Double Room ----");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleRoomAvailable);

        System.out.println("\n---- Suite Room ----");
        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteRoomAvailable);

        System.out.println("\nApplication execution completed.");
    }
}
