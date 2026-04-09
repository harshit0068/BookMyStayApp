/**
 * ====================================================================
 * MAIN CLASS - UseCase4RoomSearch
 * ====================================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * This class demonstrates how guests
 * can view available rooms without
 * modifying inventory data.
 *
 * The system enforces read-only access
 * by design and usage discipline.
 *
 * @version 4.0
 */
public class UseCase4RoomSearch {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // 1. Initialize Centralized Inventory
        RoomInventory inventory = new RoomInventory();

        // 2. Initialize Room Domain Objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // 3. Initialize Search Service
        RoomSearchService searchService = new RoomSearchService();

        // 4. Perform Room Search (Read-Only)
        searchService.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
    }
}