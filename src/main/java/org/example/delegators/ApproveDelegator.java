package org.example.delegators;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.entity.Booking;
import org.example.services.BookingService;

public class ApproveDelegator implements JavaDelegate {
    BookingService bookingService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        boolean isApproved = (boolean) execution.getVariable("approve");
        if (!isApproved) {
            {
                bookingService.rejectBooking((long) execution.getVariable("booking_id"));;

            }
        }
        bookingService.setApproved((Booking) execution.getVariable("booking_id"), isApproved);
    }
}
