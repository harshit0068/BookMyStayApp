import java.util.Map;

/**
 * ====================================================================
 * MAIN CLASS - UseCase12DataPersistenceRecovery
 * ====================================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class demonstrates how system state
 * can be restored after an application restart.
 *
 * Inventory data is loaded from a file
 * before any booking operations occur.
 *
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("System Recovery\n");

        // 1. Initialize dependencies
        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        // Define the file name where data will be saved
        String filePath = "inventory_data.txt";

        // 2. Attempt to load the inventory
        persistenceService.loadInventory(inventory, filePath);

        // 3. Display current inventory
        System.out.println("\nCurrent Inventory:");
        Map<String, Integer> avail = inventory.getRoomAvailability();
        System.out.println("Single: " + avail.get("Single Room"));
        System.out.println("Double: " + avail.get("Double Room"));
        System.out.println("Suite: " + avail.get("Suite Room") + "\n");

        // 4. Save the inventory to the file before shutting down
        persistenceService.saveInventory(inventory, filePath);
    }
}