package com.paulmagbor.lil.learningspring.Web;

import java.util.List;

import com.paulmagbor.lil.learningspring.business.ReservationService;
import com.paulmagbor.lil.learningspring.data.Guest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model) {
        List<Guest> hotelGuests = reservationService.getHotelGuests();

        model.addAttribute("guests", hotelGuests);

        return "hotel-guests";
    }
}
