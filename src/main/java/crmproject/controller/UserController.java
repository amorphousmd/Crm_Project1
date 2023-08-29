package crmproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userController", urlPatterns = {"/user-table", "/user-add", "/user-details"})
public class UserController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch (path) {
		case "/user-add":
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		
		case "/user-details":
			req.getRequestDispatcher("user-details.jsp").forward(req, resp);
			break;
		
		case "/user-table":
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
			
		default:
			break;
		}
	}
}
