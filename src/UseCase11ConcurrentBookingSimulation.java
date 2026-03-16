import java.util.*;

/*
 Book My Stay App
 Use Case 11: Concurrent Booking Simulation (Thread Safety)
*/

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay - Concurrent Booking");
        System.out.println("Use Case 11 Thread Safety");
        System.out.println("=================================\n");

        SharedBookingProcessor11 sharedBookingProcessor11 =
                new SharedBookingProcessor11();

        sharedBookingProcessor11.addBookingRequest11(
                new BookingRequestRecord11("GuestA", "Standard"));

        sharedBookingProcessor11.addBookingRequest11(
                new BookingRequestRecord11("GuestB", "Standard"));

        sharedBookingProcessor11.addBookingRequest11(
                new BookingRequestRecord11("GuestC", "Deluxe"));

        sharedBookingProcessor11.addBookingRequest11(
                new BookingRequestRecord11("GuestD", "Suite"));

        Thread bookingThreadA11 =
                new Thread(new ConcurrentBookingWorker11(sharedBookingProcessor11));

        Thread bookingThreadB11 =
                new Thread(new ConcurrentBookingWorker11(sharedBookingProcessor11));

        Thread bookingThreadC11 =
                new Thread(new ConcurrentBookingWorker11(sharedBookingProcessor11));

        bookingThreadA11.start();
        bookingThreadB11.start();
        bookingThreadC11.start();

        try {

            bookingThreadA11.join();
            bookingThreadB11.join();
            bookingThreadC11.join();

        } catch (InterruptedException exceptionObject11) {

            exceptionObject11.printStackTrace();
        }

        System.out.println("\nAll booking requests processed.");
    }
}


/* Booking request entity */
class BookingRequestRecord11 {

    private String guestName11;
    private String roomType11;

    public BookingRequestRecord11(String guestNameInput11, String roomTypeInput11) {

        guestName11 = guestNameInput11;
        roomType11 = roomTypeInput11;
    }

    public String getGuestName11() {
        return guestName11;
    }

    public String getRoomType11() {
        return roomType11;
    }
}


/* Shared booking processor */
class SharedBookingProcessor11 {

    private Queue<BookingRequestRecord11> bookingQueue11 =
            new LinkedList<>();

    private Map<String, Integer> inventoryMap11 =
            new HashMap<>();

    public SharedBookingProcessor11() {

        inventoryMap11.put("Standard", 2);
        inventoryMap11.put("Deluxe", 1);
        inventoryMap11.put("Suite", 1);
    }

    public synchronized void addBookingRequest11(
            BookingRequestRecord11 bookingRequestInput11) {

        bookingQueue11.add(bookingRequestInput11);

        System.out.println(
                "Request added for "
                        + bookingRequestInput11.getGuestName11()
                        + " ("
                        + bookingRequestInput11.getRoomType11()
                        + ")"
        );
    }


    public synchronized BookingRequestRecord11 fetchNextRequest11() {

        return bookingQueue11.poll();
    }


    public synchronized void processBookingAllocation11(
            BookingRequestRecord11 bookingRequestInput11) {

        String roomTypeKey11 = bookingRequestInput11.getRoomType11();

        int availableCount11 = inventoryMap11.getOrDefault(roomTypeKey11, 0);

        if (availableCount11 > 0) {

            inventoryMap11.put(roomTypeKey11, availableCount11 - 1);

            System.out.println(
                    Thread.currentThread().getName()
                            + " allocated "
                            + roomTypeKey11
                            + " room to "
                            + bookingRequestInput11.getGuestName11()
            );

        } else {

            System.out.println(
                    Thread.currentThread().getName()
                            + " booking failed for "
                            + bookingRequestInput11.getGuestName11()
                            + " (No "
                            + roomTypeKey11
                            + " rooms available)"
            );
        }
    }
}


/* Worker thread */
class ConcurrentBookingWorker11 implements Runnable {

    private SharedBookingProcessor11 sharedBookingProcessor11;

    public ConcurrentBookingWorker11(
            SharedBookingProcessor11 processorInput11) {

        sharedBookingProcessor11 = processorInput11;
    }

    public void run() {

        while (true) {

            BookingRequestRecord11 requestRecord11 =
                    sharedBookingProcessor11.fetchNextRequest11();

            if (requestRecord11 == null) {
                break;
            }

            sharedBookingProcessor11.processBookingAllocation11(
                    requestRecord11
            );

            try {

                Thread.sleep(200);

            } catch (InterruptedException exceptionObject11) {

                exceptionObject11.printStackTrace();
            }
        }
    }
}
