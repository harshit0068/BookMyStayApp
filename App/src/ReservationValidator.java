/**
 * ====================================================================
 * CLASS - ReservationValidator
 * ====================================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * This class is responsible for validating booking requests
 * before they are processed. All validation rules are centralized
 * to avoid duplication and inconsistency.
 *
 * @version 9.0
 */
public class ReservationValidator {

    /**
     * Validates booking input provided by the user.
     *
     * @param guestName name of the guest
     * @param roomType requested room type
     * @param inventory centralized inventory
     * @throws InvalidBookingException if validation fails
     */
    public void validate(String guestName, String roomType, RoomInventory inventory) throws InvalidBookingException {
        // 1. Validate Guest Name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // 2. Validate Room Type (Case Sensitive Check)
        // Note: Our inventory uses keys like "Single Room", so we append " Room" to the input for checking
        String mappedRoomType = roomType + " Room";
        if (!inventory.getRoomAvailability().containsKey(mappedRoomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // 3. Validate Availability
        if (inventory.getRoomAvailability().get(mappedRoomType) <= 0) {
            throw new InvalidBookingException("Selected room type is fully booked.");
        }
    }
}