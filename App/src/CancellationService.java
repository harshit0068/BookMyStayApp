import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ====================================================================
 * CLASS - CancellationService
 * ====================================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class is responsible for handling booking cancellations.
 * It ensures that:
 * - Cancelled room IDs are tracked
 * - Inventory is restored correctly
 * - Invalid cancellations are prevented
 *
 * A stack is used to model rollback behavior.
 *
 * @version 10.0
 */
public class CancellationService {

    /** Stack to track recently released room IDs. */
    private Stack<String> releasedRoomIds;

    /** Maps reservation ID to room type. */
    private Map<String, String> reservationRoomTypeMap;

    /** Initializes cancellation tracking structures. */
    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers a confirmed booking.
     * This method simulates storing confirmation data
     * that will later be required for cancellation.
     *
     * @param reservationId confirmed reservation ID
     * @param roomType allocated room type
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    /**
     * Cancels a confirmed booking and restores inventory safely.
     *
     * @param reservationId reservation to cancel
     * @param inventory centralized room inventory
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {
        // 1. Validate reservation exists
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation ID " + reservationId + " not found.");
            return;
        }

        // 2. Identify the room type
        String roomType = reservationRoomTypeMap.get(reservationId);

        // 3. Increment inventory count
        int currentAvailability = inventory.getRoomAvailability().get(roomType);
        inventory.updateAvailability(roomType, currentAvailability + 1);

        // 4. Record the rollback in the stack
        releasedRoomIds.push(reservationId);

        // 5. Remove from active reservations
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    /**
     * Displays recently cancelled reservations.
     * This method helps visualize rollback order.
     */
    public void showRollbackHistory() {
        System.out.println("Rollback History (Most Recent First):");
        // Clone the stack to display it without emptying it
        @SuppressWarnings("unchecked")
        Stack<String> displayStack = (Stack<String>) releasedRoomIds.clone();

        while (!displayStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + displayStack.pop());
        }
    }
}