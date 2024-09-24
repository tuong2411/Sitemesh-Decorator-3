package vn.iostar.Controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.Models.UserModel;

import vn.iostar.Services.impl.UserServices;
import vn.iostar.Services.*;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/Login")
public class LoginControllers extends HttpServlet {
	
	iUserServices service = new UserServices();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
		req.getRequestDispatcher("/Views/Login.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("uname");
		String password = req.getParameter("psw");
		boolean isRememberMe = false;
		
		String remember = req.getParameter("remember");
		
		if("on".equals(remember)) {
			isRememberMe = true;
		}
		String alertMsg = "";
		if(username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tai khoan hoac mat khau khong duoc rong";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/Views/Login.jsp").forward(req, resp);
			return;
		}
		UserModel user = service.login(username, password);
		if(user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			if(isRememberMe) {
				saveRememberMe(resp, username);
			}
			resp.sendRedirect(req.getContextPath()+ "/waiting");
		}
		else {
			alertMsg ="Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/Views/Login.jsp").forward(req, resp);
		}
    }
	private void saveRememberMe(HttpServletResponse resp, String username) {
		// TODO Auto-generated method stub
		Cookie cookie = new Cookie("username", username);
		cookie.setMaxAge(60*60*24);
		resp.addCookie(cookie);
	}
}
