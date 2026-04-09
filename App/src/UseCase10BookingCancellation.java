/**
 * @version 10.0
 */
public class UseCase10BookingCancellation {

    public static void main(String[] args) {
        System.out.println("Booking Cancellation\n");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        // Simulate a booking that was already made
        String reservationId = "Single-1";
        String roomType = "Single Room"; 
        
        // Let's assume inventory dropped to 4 after booking
        inventory.updateAvailability(roomType, 4); 
        cancellationService.registerBooking(reservationId, roomType);

        // Cancel the booking
        cancellationService.cancelBooking(reservationId, inventory);

        // Show Rollback History
        cancellationService.showRollbackHistory();

        // Display updated inventory
        int newAvailability = inventory.getRoomAvailability().get(roomType);
        System.out.println("Updated Single Room Availability: " + newAvailability);
    }
}
