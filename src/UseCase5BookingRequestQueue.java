import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay - Hotel Booking System
 *
 * Use Case 5: Booking Request Queue
 * Demonstrates FIFO booking request handling using Queue.
 *
 * @version 5.1
 */
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 5.1");
        System.out.println(" Booking Request Queue System");
        System.out.println("=================================\n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulated guest booking requests
        bookingQueue.addReservation(new Reservation("Alice", "Single Room"));
        bookingQueue.addReservation(new Reservation("Bob", "Double Room"));
        bookingQueue.addReservation(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addReservation(new Reservation("Diana", "Single Room"));

        System.out.println("\nCurrent Booking Queue:\n");

        bookingQueue.displayQueue();
    }
}

/**
 * Reservation represents a guest booking request.
 *
 * @version 5.0
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

/**
 * BookingRequestQueue manages incoming booking requests
 * using FIFO queue ordering.
 *
 * @version 5.0
 */
class BookingRequestQueue{

    private Queue<Reservation> reservationQueue;

    public BookingRequestQueue() {
        reservationQueue = new LinkedList<>();
    }

    /**
     * Adds a reservation request to the queue.
     */
    public void addReservation(Reservation reservation) {

        reservationQueue.add(reservation);

        System.out.println("Booking request received from "
                + reservation.getGuestName()
                + " for "
                + reservation.getRoomType());
    }

    /**
     * Displays the current request queue.
     */
    public void displayQueue() {

        for (Reservation r : reservationQueue) {
            r.displayReservation();
        }
    }
}