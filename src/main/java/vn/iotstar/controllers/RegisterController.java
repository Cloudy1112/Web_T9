package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = { "/register", "/dang-ky" })
public class RegisterController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/register.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username_reg").toString();
		String password = req.getParameter("password_reg").toString();
		String reppassword = req.getParameter("psw-repeat").toString();
		
		IUserService service = new UserServiceImpl();
		String alert = "";


		if (!password.equals(reppassword)) {
			alert = "Mật khẩu và mật khẩu nhập lại không khớp!";
			req.setAttribute("alert_reg", alert);
			req.getRequestDispatcher("views/register.jsp").forward(req, resp);
		} else if (service.checkExistUsername(username) == true) {
			alert = "Username đã tồn tại!";
			req.setAttribute("alert_reg", alert);
			req.getRequestDispatcher("views/register.jsp").forward(req, resp);
		} else {
			UserModel user = new UserModel();
			user.setUsername(username);
			user.setPassword(password);
			
			service.insert(user);
			alert = "Đăng ký thành công!";
			req.setAttribute("alert_reg", alert);
			req.getRequestDispatcher("views/login.jsp").forward(req, resp);
		}

	}

}
