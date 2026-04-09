import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ====================================================================
 * CLASS - AddOnServiceManager
 * ====================================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class manages optional services
 * associated with confirmed reservations.
 *
 * It supports attaching multiple services
 * to a single reservation.
 *
 * @version 7.0
 */
public class AddOnServiceManager {

    /**
     * Maps reservation ID to selected services.
     * Key -> Reservation ID
     * Value -> List of selected services
     */
    private Map<String, List<Service>> servicesByReservation;

    /** Initializes the service manager. */
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    /**
     * Attaches a service to a reservation.
     *
     * @param reservationId confirmed reservation ID
     * @param service add-on service
     */
    public void addService(String reservationId, Service service) {
        // If the reservation doesn't have a list yet, create an empty one
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        // Add the new service to the reservation's list
        servicesByReservation.get(reservationId).add(service);
    }

    /**
     * Calculates total add-on cost for a reservation.
     *
     * @param reservationId reservation ID
     * @return total service cost
     */
    public double calculateTotalServiceCost(String reservationId) {
        double total = 0.0;
        List<Service> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (Service service : services) {
                total += service.getCost();
            }
        }
        return total;
    }
}