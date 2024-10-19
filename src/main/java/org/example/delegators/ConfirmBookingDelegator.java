package org.example.delegators;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.services.BookingService;


//@Named("confirmBookingDelegator")
public class ConfirmBookingDelegator implements JavaDelegate {
    BookingService bookingService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        boolean checkinConfirmation = (boolean) execution.getVariable("checkinConfirmation");
        long bookingId = (long) execution.getVariable("booking_id");
        System.out.println("Check-in booking: " + bookingId);
        execution.setVariable("checkinConfirmation", true);
        if (bookingService.checkExistence(bookingId) && checkinConfirmation) {
            {
                bookingService.setApproved(bookingService.getBooking(bookingId), true);

            }
        }
    }
}


