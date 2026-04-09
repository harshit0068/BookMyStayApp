/**
 * ====================================================================
 * MAIN CLASS - UseCase6RoomAllocation
 * ====================================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class demonstrates how booking
 * requests are confirmed and rooms
 * are allocated safely.
 *
 * It consumes booking requests in FIFO
 * order and updates inventory immediately.
 *
 * @version 6.0
 */
public class UseCase6RoomAllocation {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Room Allocation Processing\n");

        // 1. Initialize dependencies
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        // 2. Add requests to the queue (Note: matching the exact string keys from UC3)
        queue.addRequest(new Reservation("Abhi", "Single Room"));
        queue.addRequest(new Reservation("Subha", "Single Room"));
        queue.addRequest(new Reservation("Vanmathi", "Suite Room"));

        // 3. Process the queue
        while (queue.hasPendingRequests()) {
            Reservation nextRequest = queue.getNextRequest();
            allocationService.allocateRoom(nextRequest, inventory);
        }
    }
}