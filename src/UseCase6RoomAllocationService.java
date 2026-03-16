import java.util.*;

/**
 * Book My Stay - Hotel Booking System
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * @version 6.1
 */
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 6.1");
        System.out.println(" Reservation Allocation System");
        System.out.println("=================================\n");

        RoomInventory6 inventory6 = new RoomInventory6();
        BookingQueue6 bookingQueue6 = new BookingQueue6();
        RoomAllocationService6 allocationService6 = new RoomAllocationService6(inventory6);

        bookingQueue6.addReservation6(new Reservation6("Alice", "Single"));
        bookingQueue6.addReservation6(new Reservation6("Bob", "Double"));
        bookingQueue6.addReservation6(new Reservation6("Charlie", "Suite"));
        bookingQueue6.addReservation6(new Reservation6("Diana", "Single"));

        System.out.println("\nProcessing Booking Requests...\n");

        while (!bookingQueue6.isEmpty6()) {

            Reservation6 request6 = bookingQueue6.dequeueReservation6();
            allocationService6.confirmReservation6(request6);
        }

        System.out.println("\nFinal Allocation State:\n");
        allocationService6.displayAllocations6();
    }
}

/**
 * Reservation object for Use Case 6
 */
class Reservation6 {

    private String guestName6;
    private String roomType6;

    public Reservation6(String guestName6, String roomType6) {
        this.guestName6 = guestName6;
        this.roomType6 = roomType6;
    }

    public String getGuestName6() {
        return guestName6;
    }

    public String getRoomType6() {
        return roomType6;
    }
}

/**
 * FIFO booking request queue
 */
class BookingQueue6 {

    private Queue<Reservation6> reservationQueue6 = new LinkedList<>();

    public void addReservation6(Reservation6 reservation6) {

        reservationQueue6.add(reservation6);

        System.out.println(
                "Booking request received from "
                        + reservation6.getGuestName6()
                        + " for "
                        + reservation6.getRoomType6()
                        + " room");
    }

    public Reservation6 dequeueReservation6() {
        return reservationQueue6.poll();
    }

    public boolean isEmpty6() {
        return reservationQueue6.isEmpty();
    }
}

/**
 * Inventory management for Use Case 6
 */
class RoomInventory6 {

    private Map<String, Integer> inventoryMap6 = new HashMap<>();

    public RoomInventory6() {

        inventoryMap6.put("Single", 2);
        inventoryMap6.put("Double", 2);
        inventoryMap6.put("Suite", 1);
    }

    public int getAvailability6(String roomType6) {
        return inventoryMap6.getOrDefault(roomType6, 0);
    }

    public void decrementInventory6(String roomType6) {

        inventoryMap6.put(
                roomType6,
                inventoryMap6.get(roomType6) - 1
        );
    }
}

/**
 * Room allocation and reservation confirmation
 */
class RoomAllocationService6 {

    private RoomInventory6 inventoryService6;

    private Set<String> allocatedRoomIdSet6 = new HashSet<>();

    private Map<String, Set<String>> roomAllocationMap6 = new HashMap<>();

    private int roomIdCounter6 = 1;

    public RoomAllocationService6(RoomInventory6 inventoryService6) {
        this.inventoryService6 = inventoryService6;
    }

    public void confirmReservation6(Reservation6 reservation6) {

        String requestedRoomType6 = reservation6.getRoomType6();

        if (inventoryService6.getAvailability6(requestedRoomType6) <= 0) {

            System.out.println(
                    "No available "
                            + requestedRoomType6
                            + " rooms for "
                            + reservation6.getGuestName6()
            );

            return;
        }

        String generatedRoomId6 = generateRoomId6(requestedRoomType6);

        allocatedRoomIdSet6.add(generatedRoomId6);

        roomAllocationMap6
                .computeIfAbsent(requestedRoomType6, k -> new HashSet<>())
                .add(generatedRoomId6);

        inventoryService6.decrementInventory6(requestedRoomType6);

        System.out.println(
                "Reservation confirmed for "
                        + reservation6.getGuestName6()
                        + " | Room ID: "
                        + generatedRoomId6
        );
    }

    private String generateRoomId6(String roomType6) {

        String generatedRoomId6;

        do {

            generatedRoomId6 =
                    roomType6.substring(0, 1).toUpperCase()
                            + roomIdCounter6++;

        } while (allocatedRoomIdSet6.contains(generatedRoomId6));

        return generatedRoomId6;
    }

    public void displayAllocations6() {

        for (Map.Entry<String, Set<String>> entry6 : roomAllocationMap6.entrySet()) {

            System.out.println(
                    entry6.getKey()
                            + " Rooms Allocated: "
                            + entry6.getValue()
            );
        }
    }
}