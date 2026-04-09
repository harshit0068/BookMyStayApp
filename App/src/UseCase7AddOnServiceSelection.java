/**
 * ====================================================================
 * MAIN CLASS - UseCase7AddOnServiceSelection
 * ====================================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class demonstrates how optional
 * services can be attached to a confirmed booking.
 *
 * Services are added after room allocation
 * and do not affect inventory.
 *
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {

    /**
     * Application entry point.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Add-On Service Selection\n");

        // 1. Initialize the Service Manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // 2. Define a confirmed reservation ID (simulating output from UC6)
        String reservationId = "Single-1";

        // 3. Create available services to reach the 1500.0 total
        Service breakfast = new Service("Breakfast", 500.0);
        Service spa = new Service("Spa", 1000.0);

        // 4. Attach services to the reservation
        serviceManager.addService(reservationId, breakfast);
        serviceManager.addService(reservationId, spa);

        // 5. Calculate and display the total
        double totalCost = serviceManager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}