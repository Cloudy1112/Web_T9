package vn.iotstar.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.cj.xdevapi.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/login", "/dang-nhap" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 6427368976302556210L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Session: tham số true sẽ tạo ra session mới 
		//			nếu như không tồn tại session trước đó
		HttpSession session = req.getSession(false);
	
		//Kiểm tra session đăng nhập đó còn hạn không
		//Nếu còn hạn sẽ chuyển trang tiếp tục /waiting
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}
		// check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/waiting");
					return;
				}
			}
		}

		req.getRequestDispatcher("views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		boolean isRememberMe = false;

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");

		req.setAttribute("uname", username);
		req.setAttribute("pass", password);

		// remember me kiểm tra checked box
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		//Xét điều kiện nhập thiếu thông tin
		String alertMsg = "";
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
		}
		//Tạo thực hiện kiểm tra login
		//Sinh ra User nếu login có tài khoản và passwword trùng khớp với DB
		IUserService service = new UserServiceImpl();
		UserModel user = service.login(username, password);
		if (user != null) {
			// Session: tham số true sẽ tạo ra đối tượng nếu như chưa có đối tượng
			HttpSession session = req.getSession(true);
			session.setMaxInactiveInterval(30); 	//Thời gian Session tồn tại
			session.setAttribute("account", user);

			// Xét khi tích vào ô remember
			if (isRememberMe) {
				// Tao Cookie
				saveRememberMe(resp, username);
			}
			
			// Chuyen huong khi dang nhap thanh cong
			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}

	}

	public static final String SESSION_USERNAME = "username";
	public static final String COOKIE_REMEMBER = "username";

	private void saveRememberMe(HttpServletResponse resp, String username) {

		Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
		cookie.setMaxAge(60);
		resp.addCookie(cookie);  
	}

}
