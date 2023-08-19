package crmproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmproject.service.LoginService;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginService loginService = new LoginService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String matkhau = req.getParameter("password");
//		Cookie isAdminCookie = new Cookie("isAdmin", "true");
		boolean isSuccess = loginService.checkLogin(email, matkhau, req);
		
		if (isSuccess) {
			System.out.println("Success");
		}
		else {
			System.out.println("Failed");
		}
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
}
