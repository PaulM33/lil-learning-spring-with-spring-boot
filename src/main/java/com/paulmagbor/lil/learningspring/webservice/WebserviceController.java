package com.paulmagbor.lil.learningspring.webservice;

import java.util.Date;
import java.util.List;

import com.paulmagbor.lil.learningspring.business.ReservationService;
import com.paulmagbor.lil.learningspring.business.RoomReservation;
import com.paulmagbor.lil.learningspring.util.DateUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
}
