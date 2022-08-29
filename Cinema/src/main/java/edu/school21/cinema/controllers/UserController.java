package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Logs;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.User;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.LogAuthService;
import edu.school21.cinema.services.SessionService;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static edu.school21.cinema.models.Avatar.createAvatarList;

@Controller
@RequestMapping
@SessionAttributes("user")
public class UserController {
	@Autowired
	private LogAuthService logAuthService;

	@Autowired
	private FilmService filmService;

	@Autowired
	UserService userService;

	@Autowired
	private SessionService sessionService;

	@ModelAttribute("user")
	public User createUser() { return new User(); }

	@GetMapping ("profile")
	public String openProfile(@CookieValue(value = "email", required = false) String emailCookie, Model model) {
		if (emailCookie != null) {
			User user = userService.findByEmail(emailCookie);
			model.addAttribute("user", user);
			model.addAttribute("lstAvatar", createAvatarList(user));
			System.out.println("Avatar_List: " + createAvatarList(user));
			return "profile";
		}
		return "redirect:/signIn";
	}

	@PostMapping ("profile")
	public String openProfile(@RequestParam("email") String email,
							  @RequestParam("password") String pass,
							  HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
			User user = userService.findByEmail(email);
			if (user != null && pass.equals(user.getPassword())) {
				Logs log = new Logs();
				LocalTime now = LocalTime.now();
				LocalDate now1 = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				String data = "" + now1.getMonth() + " " + now1.getDayOfMonth() + ", " + now1.getYear() + "";
				log.setTime(now.format(formatter));
				log.setDate(data);
				log.setEmail(user.getEmail());
				log.setIp(request.getRemoteAddr());
				logAuthService.save(log);
				Cookie cookie = new Cookie("email", user.getEmail());
				cookie.setMaxAge(60*10);
				response.addCookie(cookie);
				model.addAttribute("lstAvatar", createAvatarList(user));
				model.addAttribute("logs", logAuthService.findAllLogsByEmail(user.getEmail()));
				model.addAttribute("user", user);
				return "profile";
			}
		return "redirect:/signIn";
	}

	@GetMapping("signUp")
	public String startSignUp(@CookieValue(value = "email", required = false) String emailCookie, Model model) {
		if (emailCookie != null) {
			User user = userService.findByEmail(emailCookie);
			model.addAttribute("user", user);
			model.addAttribute("lstAvatar", createAvatarList(user));
			return "redirect:/profile";
		}
		return "signUp";
	}

	@PostMapping("signUp")
	public String addUser(@ModelAttribute User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(user.toString());
		if (userService.findByEmail(user.getEmail()) == null) {
			user.setIsAdmin(false);
			userService.save(user);
			Logs log = new Logs();
			LocalTime now = LocalTime.now();
			LocalDate now1 = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String data = "" + now1.getMonth() + " " + now1.getDayOfMonth() + ", " + now1.getYear() + "";
			log.setTime(now.format(formatter));
			log.setDate(data);
			log.setEmail(user.getEmail());
			log.setIp(request.getRemoteAddr());
			logAuthService.save(log);
			Cookie cookie = new Cookie("email", user.getEmail());
			cookie.setMaxAge(60*10);
			response.addCookie(cookie);
			model.addAttribute("lstAvatar", createAvatarList(user));
			model.addAttribute("logs", logAuthService.findAllLogsByEmail(user.getEmail()));
			model.addAttribute("user", user);
			return "redirect:/profile";
		}
		return "redirect:/signIn";
	}

	@GetMapping("signIn")
	public String startSignIn(@CookieValue(value = "email", required = false) String emailCookie, Model model) {
		if (emailCookie != null) {
			User user = userService.findByEmail(emailCookie);
			model.addAttribute("user", user);
			model.addAttribute("lstAvatar", createAvatarList(user));
			return "redirect:/profile";
		}
		return "signIn";
	}

	@GetMapping("admin")
	public String startAdmin() {
		return "indexToAdmin";
	}

	@GetMapping("user")
	public String startUser() {
		return "indexToUser";
	}

	@GetMapping("films")
	public String showFilms (@CookieValue(value = "email", required = false) String emailCookie, Model model){
		if (emailCookie != null) {
			List<Film> films = filmService.findAll();
			model.addAttribute("films", films);
			return "showfilmUsers";
		}
		return "redirect:/signIn";
	}

	@GetMapping("sessions")
	public String showSessions (@CookieValue(value = "email", required = false) String emailCookie, Model model){
		if (emailCookie != null) {
			List<Session> sessions = sessionService.findAll();
			model.addAttribute("sessions", sessions);
			return "showsessionUsers";
		}
		return "redirect:/signIn";
	}

	@GetMapping("sessions/" + "{id}")
	public String showSessionById (@CookieValue(value = "email", required = false) String emailCookie, @PathVariable Long id, Model model){
		if (emailCookie != null) {
			Session session = sessionService.findById(id);
			model.addAttribute("session", session);
			User user = userService.findByEmail(emailCookie);
			model.addAttribute("user", user);
			return "sessiondetails";
		}
		return "redirect:/signIn";
	}

	@PostMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
		Cookie[] cookie = request.getCookies();
		for (Cookie c : cookie) {
			if (c.getName().equals("email")) {
				c.setMaxAge(0);
				response.addCookie(c);
				break;
			}
		}
		status.setComplete();
		return "redirect:/";
	}
}
