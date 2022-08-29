package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.HallService;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping(value = "/admin/panel/")
    @Controller
    public class SessionController {
        @Autowired
        private SessionService sessionService;
        @Autowired
        private FilmService filmService;
        @Autowired
        private HallService hallService;

        public SessionController() {
        }

        @GetMapping("sessions/update/{id}")
        public String updateFilm(@PathVariable("id") Long id, Model model) {
            Session session = sessionService.findById(id);
            List<Hall> halls= hallService.findAll();
            List<Film> films = filmService.findAll();
            System.out.println(halls);
            model.addAttribute("session", session);
            model.addAttribute("id", id);
            model.addAttribute("halls", halls);
            model.addAttribute("films", films);
            return "updateSession";
        }

        @PostMapping("sessions/update/{id}")
        public String updateHall (@Valid Session session, BindingResult bindingResult, Model model,
                                  @PathVariable("id") Long id) {
            System.out.println("====" + session);
            List<Session> sessions= sessionService.findAll();
            model.addAttribute("sessions", sessions);
            if (!bindingResult.hasErrors()) {
                model.addAttribute("noError", true);

                Session tempSession = sessionService.findById(id);
                tempSession.setHall(hallService.findById(session.getHall().getHallId()));
                tempSession.setFilm(filmService.findById(session.getFilm().getId()));
                tempSession.setDateTime(session.getDateTime());
                tempSession.setTicketCost(session.getTicketCost());

                sessionService.update(tempSession);
                System.out.println("====" + tempSession);
                return "redirect:/admin/panel/sessions";
            } else {
                model.addAttribute("session", session);
                return "updateSession";
            }
        }

        @GetMapping("sessions")
        public String showSessions (Model model){
            List<Session> sessions= sessionService.findAll();
            model.addAttribute("sessions", sessions);
            return "showsession";
        }

        @GetMapping("sessions/" + "{id}")
        public String showSessionById (@CookieValue(value = "email", required = false) String emailCookie, @PathVariable Long id, Model model){
            if (emailCookie != null) {
                Session session = sessionService.findById(id);
                model.addAttribute("session", session);
                return "sessiondetails";
            }
            return "redirect:/signIn";
        }
        
        @GetMapping("sessions/new")
        public String addSessions (Model model) {
            List<Hall> halls= hallService.findAll();
            List<Film> films = filmService.findAll();
            model.addAttribute("halls", halls);
            model.addAttribute("films", films);
            return "sessions";
        }

        @PostMapping("session")
        public String addSessions(
                                  @ModelAttribute("dateTime") String dateInString,
                                  @ModelAttribute("ticketCost") float cost,
                                  @ModelAttribute("film.id") Long movie_id,
                                  @ModelAttribute("hall.hallId") Long hall_id,
                                  BindingResult result
                                  ) {
            if (!result.hasErrors()) {
                Session session = new Session();
                session.setId(0L);
                session.setDateTime(LocalDateTime.parse(dateInString));
                session.setFilm(filmService.findById(movie_id));
                session.setHall(hallService.findById(hall_id));
                session.setTicketCost(BigDecimal.valueOf(cost));
                sessionService.save(session);
                return "redirect:/admin/panel/sessions";
            }
            return "sessions";
        }

        @GetMapping("sessions/delete/{id}")
        public String deleteSession(@PathVariable(value = "id") Long id){
            Session session = sessionService.findById(id);
            System.out.println("====" + session);
            sessionService.delete(session);
            return "redirect:/admin/panel/sessions";
        }
    }
