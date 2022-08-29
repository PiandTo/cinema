package edu.school21.cinema.filters;

import edu.school21.cinema.models.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null && user.getIsAdmin()) {
				return true;
			}
		}
		response.sendRedirect("/signIn");
		return false;
	}
}
