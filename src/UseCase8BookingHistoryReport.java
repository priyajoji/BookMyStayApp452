import java.util.*;

/*
 Book My Stay App
 Use Case 8: Booking History & Reporting
*/

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay - Booking History");
        System.out.println("Use Case 8 Reporting System");
        System.out.println("=================================\n");

        BookingHistoryManager8 bookingHistoryManager8 = new BookingHistoryManager8();
        BookingReportService8 bookingReportService8 = new BookingReportService8();

        ReservationRecord8 reservationA8 =
                new ReservationRecord8("RES201", "Alice", "Deluxe");

        ReservationRecord8 reservationB8 =
                new ReservationRecord8("RES202", "Bob", "Suite");

        ReservationRecord8 reservationC8 =
                new ReservationRecord8("RES203", "Charlie", "Standard");

        bookingHistoryManager8.storeConfirmedReservation8(reservationA8);
        bookingHistoryManager8.storeConfirmedReservation8(reservationB8);
        bookingHistoryManager8.storeConfirmedReservation8(reservationC8);

        System.out.println("\n---- Booking History ----");
        bookingReportService8.displayBookingHistoryReport8(
                bookingHistoryManager8.getReservationHistoryList8()
        );

        System.out.println("\n---- Booking Summary ----");
        bookingReportService8.generateBookingSummaryReport8(
                bookingHistoryManager8.getReservationHistoryList8()
        );
    }
}


/* Reservation entity for Use Case 8 */
class ReservationRecord8 {

    private String reservationId8;
    private String guestName8;
    private String roomType8;

    public ReservationRecord8(String reservationId8, String guestName8, String roomType8) {
        this.reservationId8 = reservationId8;
        this.guestName8 = guestName8;
        this.roomType8 = roomType8;
    }

    public String getReservationId8() {
        return reservationId8;
    }

    public String getGuestName8() {
        return guestName8;
    }

    public String getRoomType8() {
        return roomType8;
    }
}


/* Stores booking history */
class BookingHistoryManager8 {

    private List<ReservationRecord8> reservationHistoryList8 =
            new ArrayList<>();

    public void storeConfirmedReservation8(ReservationRecord8 reservationRecord8) {

        reservationHistoryList8.add(reservationRecord8);

        System.out.println(
                "Booking stored: "
                        + reservationRecord8.getReservationId8()
                        + " | Guest: "
                        + reservationRecord8.getGuestName8()
        );
    }

    public List<ReservationRecord8> getReservationHistoryList8() {

        return reservationHistoryList8;
    }
}


/* Generates reports from booking history */
class BookingReportService8 {

    public void displayBookingHistoryReport8(
            List<ReservationRecord8> reservationHistoryInput8) {

        for (ReservationRecord8 reservationEntry8 : reservationHistoryInput8) {

            System.out.println(
                    "Reservation ID: "
                            + reservationEntry8.getReservationId8()
                            + " | Guest: "
                            + reservationEntry8.getGuestName8()
                            + " | Room Type: "
                            + reservationEntry8.getRoomType8()
            );
        }
    }

    public void generateBookingSummaryReport8(
            List<ReservationRecord8> reservationHistoryInput8) {

        int totalBookings8 = reservationHistoryInput8.size();

        Map<String, Integer> roomTypeSummaryMap8 =
                new HashMap<>();

        for (ReservationRecord8 reservationEntry8 : reservationHistoryInput8) {

            String roomTypeKey8 = reservationEntry8.getRoomType8();

            roomTypeSummaryMap8.put(
                    roomTypeKey8,
                    roomTypeSummaryMap8.getOrDefault(roomTypeKey8, 0) + 1
            );
        }

        System.out.println("Total Bookings: " + totalBookings8);

        System.out.println("\nBookings by Room Type:");

        for (Map.Entry<String, Integer> summaryEntry8
                : roomTypeSummaryMap8.entrySet()) {

            System.out.println(
                    summaryEntry8.getKey()
                            + " : "
                            + summaryEntry8.getValue()
            );
        }
    }
}
