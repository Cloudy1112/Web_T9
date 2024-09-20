package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/forgot"})
public class ForgotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/forgot.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username").toString();
		String password = req.getParameter("password").toString();
		
		IUserService service = new UserServiceImpl();
		String alert = "";

		if (service.checkExistUsername(username) == false) {
			alert = "Username không tồn tại!";
			req.setAttribute("alert", alert);
			req.getRequestDispatcher("views/forgot.jsp").forward(req, resp);
		} else {
			UserModel user = new UserModel();
			user.setUsername(username);
			user.setPassword(password);
			
			service.reset_password(user);
			resp.sendRedirect(req.getContextPath()+"/login");
		}
	}
	
}
