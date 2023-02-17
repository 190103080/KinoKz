package finalproject.springproject.controllers;

import finalproject.springproject.models.*;
import finalproject.springproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CinemaController {

    @Autowired
    private HallService hallService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ReservedSeatService reservedSeatService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private KinoService kinoService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CinemaFileUploadService cinemaFileUploadService;

    @Autowired
    private KinoFileUploadService kinoFileUploadService;

    @GetMapping(value = "/allCinema")
    public String allCinema(Model model) {
        model.addAttribute("cinemas", cinemaService.getCinemas());
        return "allCinema";
    }

    @GetMapping(value = "/detailsCinema/{id}")
    public String detailsCinema(@PathVariable(name = "id") Long id, Model model) {
        CinemaModel cinemaModel = cinemaService.getCinema(id);
        model.addAttribute("cinemas", cinemaModel);

        return "detailsCinema";
    }

    @GetMapping(value = "/detailsKino/{id}")
    public String detailsKino(@PathVariable(name = "id") Long id, Model model) {
        CinemaModel cinemaModel = cinemaService.getCinema(id);
        model.addAttribute("cinemas", cinemaModel);
        model.addAttribute("tickets", ticketService.getTickets());
        model.addAttribute("seats", seatService.getSeats());
        model.addAttribute("session", sessionService.getSessions());
//        model.addAttribute("user", userService.getUser(id));

        return "detailsKino";
    }

    @GetMapping(value = "/detailsKino/{id}/pageTicket")
    public String pageTicket(@PathVariable(name = "id") Long id, Model model) {
        CinemaModel cinemaModel = cinemaService.getCinema(id);
        Kinoteatry kinoteatry = kinoService.getKinoteatry(id);

        model.addAttribute("cinemas", cinemaService.getCinemas());
        model.addAttribute("kinoteatry", kinoService.getKinoteatrys());

        return "pageTicket";
    }

    @PostMapping(value = "/saveeCinema")
    public String saveeCinema(@RequestParam(name = "image_pic") MultipartFile multipartFile, CinemaModel cinemaModel) {
        CinemaModel updateCinema = cinemaService.saveCinema(cinemaModel);
        cinemaFileUploadService.uploadCinemaAvatar(multipartFile, updateCinema);
        if(updateCinema != null) {
            return "redirect:/allCinema";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/viewpic/{picToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] viewPic(@PathVariable(name = "picToken") String token) throws IOException {
        return cinemaFileUploadService.getCinemaAvatar(token);
    }

    @PostMapping(value = "/deletecinema")
    public String deleteCinema(@RequestParam(name = "id") Long id) {
        cinemaService.deleteCinema(id);
        return "redirect:/";
    }

    @GetMapping(value = "/addCinemaPage")
    public String addCinemaPage() {
        return "addCinemaPage";
    }

    @PostMapping(value = "/addCinema")
    public String addCinema(CinemaModel cinemaModel) {
        cinemaService.addCinema(cinemaModel);
        return "redirect:/";
    }

    @GetMapping(value = "/detailsKino/{id}/sessionPage")
    public String addSessionPage(@PathVariable(value = "id") Long id, Model model) {
        CinemaModel cinemaModel = cinemaService.getCinema(id);

        model.addAttribute("cinema", cinemaModel);
        model.addAttribute("kinoteatry", kinoService.getKinoteatrys());
        model.addAttribute("hall", hallService.getHalls());

        return "sessionPage";
    }

    @PostMapping(value = "/addsession")
    public String addSession(Session session, Ticket ticket, Seat seat) {
        sessionService.addSession(session);
        ticketService.addTicket(ticket);
        ticketService.saveTicket(ticket, session, seat);
        return "redirect:/";
    }

    @GetMapping(value = "/ticketPage/{id}")
    public String ticketPage(@PathVariable(value = "id") Long id, Model model) {
        Ticket ticket = ticketService.getTicket(id);
//        ReservedSeat reservedSeat = reservedSeatService.getReservedSeat(id);

        model.addAttribute("ticket", ticketService.getTickets());
        model.addAttribute("seats", seatService.getSeats());
        model.addAttribute("reservedSeat", reservedSeatService.getReservedSeats());
//        model.addAttribute("reservedSeat", ticketService.getTickets());
//        model.addAttribute("user", userService.getUser(id));

        return "ticketPage";
    }

    @PostMapping(value = "/buyTicket")
    @PreAuthorize("isAuthenticated()")
    public String saveeTicket(Ticket ticket, User user, Seat seat) {
        ReservedSeat reservedSeat = new ReservedSeat();
        reservedSeatService.addReservedSeat(reservedSeat);
        Ticket updateTicket = ticketService.buyTicket(ticket, user, seat);
            if (updateTicket != null) {
                return "redirect:/";
            }
//            updateTicket = ticketService.saveTicket(ticket, session, seat);
        return "ticketPage";
    }

//    @PostMapping(value = "/buyTicket")
//    @PreAuthorize("isAuthenticated()")
//    public String buyTicket(Ticket ticket, Session session, Seat seat) {
//        ticketService.buyTicket(ticket, session);
//        return "detailsKino";
//    }
//
//    @PostMapping(value = "/buyTicket")
//    public String buyTicket(Ticket ticket, Session session) {
//        ticketService.saveTicket(ticket, session);
//        return "buyTicket";
//    }

    @GetMapping(value = "/basket")
    public String busketPage(Model model, User user) {
        model.addAttribute("ticket", ticketService.getTickets());
        model.addAttribute("localDate", LocalDate.now());
        return "basket";
    }

    @GetMapping(value = "/addKinoteatryPage")
    public String addKinoteatryPage() {
        return "addKinoteatryPage";
    }

    @GetMapping(value = "/allKinoteatry")
    public String allKinoteatry(Model model) {
        model.addAttribute("kinoteatrys", kinoService.getKinoteatrys());
        return "allKinoteatry";
    }

    @PostMapping(value = "/addKinoteatry")
    public String addKinoteatry(Kinoteatry kinoteatry) {
        kinoService.addKinoteatry(kinoteatry);
        return "redirect:/";
    }

    @PostMapping(value = "/saveeKinoteatry")
    public String saveeKinoteatry(@RequestParam(name = "kiImage_pic") MultipartFile multipartFile, Kinoteatry kinoteatry) {
        Kinoteatry updateKinoteatry = kinoService.saveKinoteatry(kinoteatry);
        kinoFileUploadService.uploadKinoteatryAvatar(multipartFile, updateKinoteatry);
        if(updateKinoteatry != null) {
            return "redirect:/allKinoteatry";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/viewki/{kiToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] viewKi(@PathVariable(name = "kiToken") String token) throws IOException {
        return kinoFileUploadService.getKinoteatryAvatar(token);
    }

    @GetMapping(value = "/detailsKinoteatry/{id}")
    public String detailsKinoteatry(@PathVariable(name = "id") Long id, Model model) {
        Kinoteatry kinoteatry = kinoService.getKinoteatry(id);
        model.addAttribute("kinoteatry", kinoteatry);

        return "detailsKinoteatry";
    }

    @PostMapping(value = "/deleteKinoteatry")
    public String deleteKinoteatry(@RequestParam(name = "id") Long id) {
        kinoService.deleteKinoteatry(id);
        return "redirect:/";
    }

}
