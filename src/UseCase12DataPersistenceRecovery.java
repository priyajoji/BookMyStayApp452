import java.io.*;
import java.util.*;

/*
 Book My Stay App
 Use Case 12: Data Persistence & System Recovery
*/

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay - Persistence System");
        System.out.println("Use Case 12 Data Recovery");
        System.out.println("=================================\n");

        PersistenceService12 persistenceService12 = new PersistenceService12();

        SystemStateContainer12 systemStateContainer12 =
                persistenceService12.loadSystemState12();

        if (systemStateContainer12 == null) {

            systemStateContainer12 = new SystemStateContainer12();

            systemStateContainer12.getInventoryMap12().put("Standard", 5);
            systemStateContainer12.getInventoryMap12().put("Deluxe", 3);
            systemStateContainer12.getInventoryMap12().put("Suite", 2);

            systemStateContainer12.getBookingHistoryList12().add(
                    new BookingRecordEntity12("RES501", "Alice", "Standard")
            );

            systemStateContainer12.getBookingHistoryList12().add(
                    new BookingRecordEntity12("RES502", "Bob", "Deluxe")
            );

            System.out.println("New system state created.");
        }

        System.out.println("\nCurrent Inventory State:");

        for (Map.Entry<String, Integer> entryObject12 :
                systemStateContainer12.getInventoryMap12().entrySet()) {

            System.out.println(entryObject12.getKey() + " : " + entryObject12.getValue());
        }

        System.out.println("\nBooking History:");

        for (BookingRecordEntity12 bookingRecord12 :
                systemStateContainer12.getBookingHistoryList12()) {

            System.out.println(
                    bookingRecord12.getReservationId12()
                            + " | "
                            + bookingRecord12.getGuestName12()
                            + " | "
                            + bookingRecord12.getRoomType12()
            );
        }

        persistenceService12.saveSystemState12(systemStateContainer12);

        System.out.println("\nSystem state saved successfully.");
    }
}


/* Serializable booking record */
class BookingRecordEntity12 implements Serializable {

    private String reservationId12;
    private String guestName12;
    private String roomType12;

    public BookingRecordEntity12(String reservationIdInput12,
                                 String guestNameInput12,
                                 String roomTypeInput12) {

        reservationId12 = reservationIdInput12;
        guestName12 = guestNameInput12;
        roomType12 = roomTypeInput12;
    }

    public String getReservationId12() {
        return reservationId12;
    }

    public String getGuestName12() {
        return guestName12;
    }

    public String getRoomType12() {
        return roomType12;
    }
}


/* Container for system state */
class SystemStateContainer12 implements Serializable {

    private Map<String, Integer> inventoryMap12 = new HashMap<>();

    private List<BookingRecordEntity12> bookingHistoryList12 = new ArrayList<>();

    public Map<String, Integer> getInventoryMap12() {
        return inventoryMap12;
    }

    public List<BookingRecordEntity12> getBookingHistoryList12() {
        return bookingHistoryList12;
    }
}


/* Handles persistence operations */
class PersistenceService12 {

    private final String persistenceFileName12 = "booking_system_state12.dat";


    public void saveSystemState12(SystemStateContainer12 stateInput12) {

        try {

            FileOutputStream fileOutputStream12 =
                    new FileOutputStream(persistenceFileName12);

            ObjectOutputStream objectOutputStream12 =
                    new ObjectOutputStream(fileOutputStream12);

            objectOutputStream12.writeObject(stateInput12);

            objectOutputStream12.close();

        } catch (IOException exceptionObject12) {

            System.out.println("Error saving system state.");
        }
    }


    public SystemStateContainer12 loadSystemState12() {

        try {

            FileInputStream fileInputStream12 =
                    new FileInputStream(persistenceFileName12);

            ObjectInputStream objectInputStream12 =
                    new ObjectInputStream(fileInputStream12);

            SystemStateContainer12 loadedState12 =
                    (SystemStateContainer12) objectInputStream12.readObject();

            objectInputStream12.close();

            System.out.println("Previous system state restored.\n");

            return loadedState12;

        } catch (FileNotFoundException exceptionObject12) {

            System.out.println("No previous persistence file found.");

        } catch (IOException | ClassNotFoundException exceptionObject12) {

            System.out.println("Persistence file corrupted. Starting fresh.");

        }

        return null;
    }
}
