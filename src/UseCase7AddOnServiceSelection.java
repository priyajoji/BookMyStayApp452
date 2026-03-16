import java.util.*;

/**
 * Book My Stay - Hotel Booking System
 *
 * Use Case 7: Add-On Service Selection
 *
 * @version 7.1
 */
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 7.1");
        System.out.println(" Add-On Service Selection System");
        System.out.println("=================================\n");

        AddOnServiceManager7 addOnManager7 = new AddOnServiceManager7();

        String reservationId7 = "RES101";

        AddOnService7 breakfastService7 = new AddOnService7("Breakfast", 20.0);
        AddOnService7 airportPickupService7 = new AddOnService7("Airport Pickup", 40.0);
        AddOnService7 spaService7 = new AddOnService7("Spa Access", 60.0);

        addOnManager7.attachService7(reservationId7, breakfastService7);
        addOnManager7.attachService7(reservationId7, airportPickupService7);
        addOnManager7.attachService7(reservationId7, spaService7);

        System.out.println("\nServices selected for reservation: " + reservationId7 + "\n");

        addOnManager7.displayServicesForReservation7(reservationId7);

        double totalCost7 = addOnManager7.calculateAdditionalCost7(reservationId7);

        System.out.println("\nTotal Add-On Cost: $" + totalCost7);
    }
}

/**
 * Represents an optional service for Use Case 7
 */
class AddOnService7 {

    private String serviceName7;
    private double serviceCost7;

    public AddOnService7(String serviceName7, double serviceCost7) {
        this.serviceName7 = serviceName7;
        this.serviceCost7 = serviceCost7;
    }

    public String getServiceName7() {
        return serviceName7;
    }

    public double getServiceCost7() {
        return serviceCost7;
    }
}

/**
 * Manages add-on services associated with reservations
 */
class AddOnServiceManager7 {

    private Map<String, List<AddOnService7>> reservationServiceMap7 = new HashMap<>();

    public void attachService7(String reservationId7, AddOnService7 service7) {

        reservationServiceMap7
                .computeIfAbsent(reservationId7, k -> new ArrayList<>())
                .add(service7);

        System.out.println(
                "Service added: "
                        + service7.getServiceName7()
                        + " ($"
                        + service7.getServiceCost7()
                        + ") for reservation "
                        + reservationId7
        );
    }

    public void displayServicesForReservation7(String reservationId7) {

        List<AddOnService7> services7 = reservationServiceMap7.get(reservationId7);

        if (services7 == null || services7.isEmpty()) {

            System.out.println("No services selected.");
            return;
        }

        for (AddOnService7 service7 : services7) {

            System.out.println(
                    "Service: "
                            + service7.getServiceName7()
                            + " | Cost: $"
                            + service7.getServiceCost7()
            );
        }
    }

    public double calculateAdditionalCost7(String reservationId7) {

        double totalCost7 = 0.0;

        List<AddOnService7> services7 = reservationServiceMap7.get(reservationId7);

        if (services7 == null) {
            return totalCost7;
        }

        for (AddOnService7 service7 : services7) {

            totalCost7 += service7.getServiceCost7();
        }

        return totalCost7;
    }
}
