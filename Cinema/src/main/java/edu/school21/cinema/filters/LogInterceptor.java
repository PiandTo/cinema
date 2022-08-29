package edu.school21.cinema.filters;

import edu.school21.cinema.models.Logs;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.LogAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LogInterceptor implements HandlerInterceptor {
	@Autowired
	private LogAuthService logAuthService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		System.out.println("Session user:" + user.toString());
		if (user.getEmail() != null) {
			modelAndView.addObject("logs", logAuthService.findAllLogsByEmail(user.getEmail()));
		}
	}
}
