import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ====================================================================
 * CLASS - RoomAllocationService
 * ====================================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class is responsible for confirming
 * booking requests and assigning rooms.
 *
 * It ensures:
 * - Each room ID is unique
 * - Inventory is updated immediately
 * - No room is double-booked
 *
 * @version 6.0
 */
public class RoomAllocationService {

    /** Tracks all globally assigned room IDs to ensure uniqueness. */
    private Set<String> allocatedRoomIds;

    /**
     * Stores assigned room IDs by room type.
     * Key -> Room type
     * Value -> Set of assigned room IDs
     */
    private Map<String, Set<String>> assignedRoomsByType;

    /**
     * Initializes allocation tracking structures.
     */
    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    /**
     * Confirms a booking request by assigning
     * a unique room ID and updating inventory.
     *
     * @param reservation booking request
     * @param inventory centralized room inventory
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();
        Map<String, Integer> currentAvailability = inventory.getRoomAvailability();

        // 1. Check if the requested room type is available
        if (currentAvailability.get(roomType) != null && currentAvailability.get(roomType) > 0) {

            // 2. Generate unique Room ID
            String newRoomId = generateRoomId(roomType);

            // 3. Mark room as allocated
            allocatedRoomIds.add(newRoomId);

            // Add to specific room type set
            assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
            assignedRoomsByType.get(roomType).add(newRoomId);

            // 4. Decrement inventory
            int newCount = currentAvailability.get(roomType) - 1;
            inventory.updateAvailability(roomType, newCount);

            // 5. Confirm booking
            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName() +
                    ", Room ID: " + newRoomId);
        } else {
            System.out.println("Booking failed for Guest: " + reservation.getGuestName() +
                    " - No " + roomType + "s available.");
        }
    }

    /**
     * Generates a unique room ID for the given room type.
     *
     * @param roomType type of room
     * @return unique room ID
     */
    private String generateRoomId(String roomType) {
        // Simple generation logic: "RoomType-Number" based on how many exist
        int count = 1;
        if (assignedRoomsByType.containsKey(roomType)) {
            count = assignedRoomsByType.get(roomType).size() + 1;
        }

        // Strip the word " Room" from the type string to match your desired output
        String prefix = roomType.replace(" Room", "");
        return prefix + "-" + count;
    }
}