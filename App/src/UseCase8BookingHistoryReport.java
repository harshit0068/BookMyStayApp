/**
 * ====================================================================
 * MAIN CLASS - UseCase8BookingHistoryReport
 * ====================================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class demonstrates how confirmed bookings are stored and reported.
 * The system maintains an ordered audit trail of reservations.
 *
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {

    /**
     * Application entry point.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Booking History and Reporting\n");

        // 1. Initialize Booking History
        BookingHistory history = new BookingHistory();

        // 2. Add confirmed reservations (Simulating output from previous use cases)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // 3. Initialize Report Service and Generate Report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}