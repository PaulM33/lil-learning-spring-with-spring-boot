package com.paulmagbor.lil.learningspring.webservice;

import java.util.Date;
import java.util.List;

import com.paulmagbor.lil.learningspring.business.ReservationService;
import com.paulmagbor.lil.learningspring.business.RoomReservation;
import com.paulmagbor.lil.learningspring.data.Guest;
import com.paulmagbor.lil.learningspring.data.Room;
import com.paulmagbor.lil.learningspring.util.DateUtils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 NOTE: good rule is to write one class per URL endpoint, then write as many methods as necessary to handle the verbs you need to respond to
*/

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false)String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);

        return this.reservationService.getRoomReservationsForDate(date);
    }

   @GetMapping("/guests")
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addHotelGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return this.reservationService.getHotelRooms();
    }
}