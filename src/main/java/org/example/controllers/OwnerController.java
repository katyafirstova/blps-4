package org.example.controllers;


import org.example.entity.Booking;
import org.example.entity.User;
import org.example.model.AdvertisementRequest;
import org.example.model.BookingResponse;
import org.example.model.InfoResponse;
import org.example.model.Message;
import org.example.services.AdvertisementService;
import org.example.services.BookingService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;



    @PostMapping("/newAdvert")
    public InfoResponse addNewAdvertisement(@RequestBody AdvertisementRequest advertisementRequest){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadUserByUsername(name);
        advertisementService.createAdvertisement(advertisementRequest, user.getId());
        return new InfoResponse("Success", 0);

    }

    @GetMapping("/checkBooking")
    public BookingResponse getNewBooking(){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadUserByUsername(name);
        return new BookingResponse(bookingService.getAllBookings(user.getId()));
    }

    @PostMapping("/approveBooking")
    public InfoResponse approveBooking(@RequestBody long id){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadUserByUsername(name);
        Booking booking = bookingService.getBooking(id);

        if(bookingService.checkExistence(id)){
            if(advertisementService.checkOwner(booking.getAdvert_id(), user)){
                bookingService.setApproved(booking, true);
                return new InfoResponse("Бронирование подтверждено", 0);
            }
            else {
                return new InfoResponse("Бронирование вам не принадлежит", 1);
            }
        }
        else {
            return new InfoResponse("Бронирование с id '" + booking.getId() + "' нет", 1);
        }
    }


    @PostMapping("/rejectBooking")
    public InfoResponse rejectBooking(@RequestBody long id){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadUserByUsername(name);
        if(bookingService.checkExistence(id)){
            if(bookingService.rejectBooking(id)){
                /*
                try {
                    rabbitTemplate.convertAndSend("${image.processing.request.queue.name}", objectMapper.writeValueAsString("imageProcessResult"));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                 */
                return new InfoResponse("Бронирование было отклонено", 0);
            }
            else {
                return new InfoResponse("Бронирование уже было отклонено", 1);
            }
        }
        else {
            return new InfoResponse("Такого бронирования не существует", 1);
        }
    }
}
