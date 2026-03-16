import java.util.HashMap;
import java.util.Map;

/**
 * RoomInventory manages centralized room availability
 * using a HashMap data structure.
 *
 * This class acts as the single source of truth
 * for room availability in the system.
 *
 * @author Priya
 * @version 3.0
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    /**
     * Constructor initializes the room inventory.
     */
    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 3);
    }

    /**
     * Returns availability for a given room type.
     */
    public int getAvailability(String roomType) {

        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Updates availability after booking or cancellation.
     */
    public void updateAvailability(String roomType, int newCount) {

        inventory.put(roomType, newCount);
    }

    /**
     * Displays the full inventory state.
     */
    public void displayInventory() {

        System.out.println("Current Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}
/**
 * Book My Stay - Hotel Booking System
 *
 * Demonstrates centralized inventory management
 * using a HashMap to store room availability.
 *
 * @author Priya
 * @version 3.1
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 3.1");
        System.out.println(" Centralized Inventory System");
        System.out.println("=================================\n");

        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        System.out.println("\nChecking availability for Single Room...");
        System.out.println("Available: " + inventory.getAvailability("Single Room"));

        // Update inventory
        System.out.println("\nBooking a Single Room...");
        int updatedCount = inventory.getAvailability("Single Room") - 1;
        inventory.updateAvailability("Single Room", updatedCount);

        // Display updated inventory
        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();

        System.out.println("\nApplication execution completed.");
    }
}
