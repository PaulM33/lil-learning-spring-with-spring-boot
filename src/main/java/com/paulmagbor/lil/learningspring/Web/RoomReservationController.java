package com.paulmagbor.lil.learningspring.Web;

import java.util.Date;
import java.util.List;

import com.paulmagbor.lil.learningspring.business.ReservationService;
import com.paulmagbor.lil.learningspring.business.RoomReservation;
import com.paulmagbor.lil.learningspring.util.DateUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
 NOTE: good rule is to write one class per URL endpoint, then write as many methods as necessary to handle the verbs you need to respond to
*/

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public RoomReservationController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);

        model.addAttribute("roomReservations", roomReservations);

        // name of template you want to return
        return "roomres";
    }
}
