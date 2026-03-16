import java.util.*;

/*
 Book My Stay App
 Use Case 9: Error Handling & Validation
*/

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay - Validation System");
        System.out.println("Use Case 9 Error Handling");
        System.out.println("=================================\n");

        InventoryValidatorService9 inventoryValidatorService9 =
                new InventoryValidatorService9();

        try {

            inventoryValidatorService9.processBookingRequest9("Deluxe", 2);

            inventoryValidatorService9.processBookingRequest9("Suite", 1);

            inventoryValidatorService9.processBookingRequest9("Penthouse", 1);

        } catch (InvalidBookingException9 exceptionObject9) {

            System.out.println("Booking Failed: " + exceptionObject9.getMessage());

        }

        try {

            inventoryValidatorService9.processBookingRequest9("Standard", 10);

        } catch (InvalidBookingException9 exceptionObject9) {

            System.out.println("Booking Failed: " + exceptionObject9.getMessage());

        }

        System.out.println("\nSystem continues running safely.");
    }
}


/* Custom exception for invalid bookings */
class InvalidBookingException9 extends Exception {

    public InvalidBookingException9(String messageInput9) {
        super(messageInput9);
    }
}


/* Handles validation and booking checks */
class InventoryValidatorService9 {

    private Map<String, Integer> roomInventoryMap9 =
            new HashMap<>();

    public InventoryValidatorService9() {

        roomInventoryMap9.put("Standard", 5);
        roomInventoryMap9.put("Deluxe", 3);
        roomInventoryMap9.put("Suite", 2);
    }

    public void processBookingRequest9(String roomTypeInput9, int quantityInput9)
            throws InvalidBookingException9 {

        validateRoomType9(roomTypeInput9);

        validateQuantity9(quantityInput9);

        validateInventoryAvailability9(roomTypeInput9, quantityInput9);

        updateInventoryAfterBooking9(roomTypeInput9, quantityInput9);

        System.out.println(
                "Booking successful for "
                        + quantityInput9
                        + " "
                        + roomTypeInput9
                        + " room(s)"
        );
    }


    private void validateRoomType9(String roomTypeInput9)
            throws InvalidBookingException9 {

        if (!roomInventoryMap9.containsKey(roomTypeInput9)) {

            throw new InvalidBookingException9(
                    "Invalid room type: " + roomTypeInput9
            );
        }
    }


    private void validateQuantity9(int quantityInput9)
            throws InvalidBookingException9 {

        if (quantityInput9 <= 0) {

            throw new InvalidBookingException9(
                    "Booking quantity must be greater than zero."
            );
        }
    }


    private void validateInventoryAvailability9(
            String roomTypeInput9,
            int quantityInput9
    ) throws InvalidBookingException9 {

        int availableRooms9 = roomInventoryMap9.get(roomTypeInput9);

        if (quantityInput9 > availableRooms9) {

            throw new InvalidBookingException9(
                    "Not enough rooms available for type: "
                            + roomTypeInput9
                            + ". Available: "
                            + availableRooms9
            );
        }
    }


    private void updateInventoryAfterBooking9(
            String roomTypeInput9,
            int quantityInput9
    ) {

        int currentCount9 = roomInventoryMap9.get(roomTypeInput9);

        int updatedCount9 = currentCount9 - quantityInput9;

        roomInventoryMap9.put(roomTypeInput9, updatedCount9);
    }
}
