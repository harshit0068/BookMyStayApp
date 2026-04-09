import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * ====================================================================
 * CLASS - FilePersistenceService
 * ====================================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class is responsible for persisting
 * critical system state to a plain text file.
 *
 * It supports:
 * - Saving room inventory state
 * - Restoring inventory on system startup
 *
 * No database or serialization framework
 * is used in this use case.
 *
 * @version 12.0
 */
public class FilePersistenceService {

    /**
     * Saves room inventory state to a file.
     * Each line follows the format: roomType=availableCount
     *
     * @param inventory centralized room inventory
     * @param filePath path to persistence file
     */
    public void saveInventory(RoomInventory inventory, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            Map<String, Integer> avail = inventory.getRoomAvailability();

            for (Map.Entry<String, Integer> entry : avail.entrySet()) {
                writer.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Loads room inventory state from a file.
     *
     * @param inventory centralized room inventory
     * @param filePath path to persistence file
     */
    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);

        // Check if this is the first time running the app (no file exists yet)
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        // If the file exists, read it line by line and update the inventory
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    inventory.updateAvailability(roomType, count);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}