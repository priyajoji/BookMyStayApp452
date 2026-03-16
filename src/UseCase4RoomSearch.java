/**
 * RoomSearchService handles read-only search operations
 * for available rooms in the system.
 *
 * It retrieves availability from inventory and
 * room details from room domain objects.
 *
 * @version 4.0
 */
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Displays available rooms and their details.
     * This method does NOT modify inventory.
     */
    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {

            int availability = inventory.getAvailability(room.roomType);

            // Defensive check: only show rooms with availability
            if (availability > 0) {

                room.displayRoomDetails();
                System.out.println("Available Rooms: " + availability);
                System.out.println("----------------------------");
            }
        }
    }
}
/**
 * Book My Stay - Hotel Booking System
 *
 * Demonstrates room search functionality using
 * read-only access to inventory and room objects.
 *
 * @version 4.1
 */
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 4.1");
        System.out.println(" Room Search & Availability");
        System.out.println("=================================\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        Room[] rooms = {single, doubleRoom, suite};

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Perform room search
        searchService.searchAvailableRooms(rooms);

        System.out.println("\nSearch operation completed.");
    }
}