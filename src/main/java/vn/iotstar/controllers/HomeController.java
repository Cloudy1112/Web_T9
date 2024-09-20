package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/admin/home", "/manager/home", "/home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if (session != null && session.getAttribute("account") != null) {
			String url = req.getRequestURL().toString();
			String role = "";
			if (url.contains("admin")) {
				role = "admin";
				req.setAttribute("role", role);
				req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
			} else if (url.contains("manager")) {
				role = "manager";
				req.setAttribute("role", role);
				req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
			} else {
				req.setAttribute("role", role);
				req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
			}
		}else {
			resp.sendRedirect(req.getContextPath()+"/login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("account");

		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					break;
				}
			}
		}

		resp.sendRedirect(req.getContextPath()+"/login");
	}
	
	
}
