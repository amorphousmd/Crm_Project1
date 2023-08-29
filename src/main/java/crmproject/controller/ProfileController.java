package crmproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "profileController", urlPatterns = {"/profile", "/profile-edit"})
public class ProfileController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch (path) {
		case "/profile":
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			break;
		
		case "/profile-edit":
			req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
			break;
			
		default:
			break;
		}
	}
}
