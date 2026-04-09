import java.util.Map;

/**
 * ====================================================================
 * MAIN CLASS - UseCase11ConcurrentBookingSimulation
 * ====================================================================
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * This class simulates multiple users attempting to book rooms
 * at the same time. It highlights race conditions and demonstrates
 * how synchronization prevents inconsistent allocations.
 *
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation\n");

        // 1. Initialize shared components
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        // 2. Populate the queue with concurrent requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single Room"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double Room"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Subha", "Single Room"));

        // 3. Create the runnable processor
        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor(
                bookingQueue, inventory, allocationService
        );

        // 4. Create multiple threads to process the shared queue
        Thread t1 = new Thread(processor);
        Thread t2 = new Thread(processor);

        // Start concurrent processing
        t1.start();
        t2.start();

        // Wait for both threads to finish before printing inventory
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // 5. Display the final consistent state
        System.out.println("\nRemaining Inventory:");
        Map<String, Integer> avail = inventory.getRoomAvailability();
        System.out.println("Single: " + avail.get("Single Room"));
        System.out.println("Double: " + avail.get("Double Room"));
        System.out.println("Suite: " + avail.get("Suite Room"));
    }
}