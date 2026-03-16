import java.util.*;

/*
 Book My Stay App
 Use Case 10: Booking Cancellation & Inventory Rollback
*/

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay - Cancellation System");
        System.out.println("Use Case 10 Rollback Logic");
        System.out.println("=================================\n");

        CancellationService10 cancellationService10 = new CancellationService10();

        cancellationService10.createConfirmedBooking10("RES301", "Standard", "STD101");
        cancellationService10.createConfirmedBooking10("RES302", "Deluxe", "DLX201");

        System.out.println("\nAttempting cancellation...\n");

        cancellationService10.cancelReservation10("RES301");

        System.out.println("\nAttempting invalid cancellation...\n");

        cancellationService10.cancelReservation10("RES999");
    }
}


/* Reservation entity for UC10 */
class ReservationRecord10 {

    private String reservationId10;
    private String roomType10;
    private String allocatedRoomId10;

    public ReservationRecord10(String reservationId10, String roomType10, String allocatedRoomId10) {
        this.reservationId10 = reservationId10;
        this.roomType10 = roomType10;
        this.allocatedRoomId10 = allocatedRoomId10;
    }

    public String getReservationId10() {
        return reservationId10;
    }

    public String getRoomType10() {
        return roomType10;
    }

    public String getAllocatedRoomId10() {
        return allocatedRoomId10;
    }
}


/* Handles cancellation + rollback */
class CancellationService10 {

    private Map<String, ReservationRecord10> reservationStorageMap10 =
            new HashMap<>();

    private Map<String, Integer> roomInventoryMap10 =
            new HashMap<>();

    private Stack<String> rollbackRoomIdStack10 =
            new Stack<>();

    public CancellationService10() {

        roomInventoryMap10.put("Standard", 5);
        roomInventoryMap10.put("Deluxe", 3);
        roomInventoryMap10.put("Suite", 2);
    }


    /* Simulates confirmed booking creation */
    public void createConfirmedBooking10(
            String reservationIdInput10,
            String roomTypeInput10,
            String roomIdInput10
    ) {

        ReservationRecord10 reservationRecord10 =
                new ReservationRecord10(reservationIdInput10, roomTypeInput10, roomIdInput10);

        reservationStorageMap10.put(reservationIdInput10, reservationRecord10);

        int currentInventory10 = roomInventoryMap10.get(roomTypeInput10);
        roomInventoryMap10.put(roomTypeInput10, currentInventory10 - 1);

        System.out.println(
                "Booking confirmed: "
                        + reservationIdInput10
                        + " | Room: "
                        + roomIdInput10
        );
    }


    /* Performs cancellation + rollback */
    public void cancelReservation10(String reservationIdInput10) {

        if (!reservationStorageMap10.containsKey(reservationIdInput10)) {

            System.out.println("Cancellation failed: Reservation does not exist.");
            return;
        }

        ReservationRecord10 reservationRecord10 =
                reservationStorageMap10.get(reservationIdInput10);

        String releasedRoomId10 = reservationRecord10.getAllocatedRoomId10();
        String roomType10 = reservationRecord10.getRoomType10();

        rollbackRoomIdStack10.push(releasedRoomId10);

        int currentInventory10 = roomInventoryMap10.get(roomType10);
        roomInventoryMap10.put(roomType10, currentInventory10 + 1);

        reservationStorageMap10.remove(reservationIdInput10);

        System.out.println(
                "Reservation cancelled successfully: "
                        + reservationIdInput10
        );

        System.out.println(
                "Room released back to inventory: "
                        + releasedRoomId10
        );

        System.out.println(
                "Rollback stack updated (top): "
                        + rollbackRoomIdStack10.peek()
        );
    }
}
