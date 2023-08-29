package crmproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "groupWorkController", urlPatterns = {"/groupwork", "/groupwork-add", "/groupwork-details"})
public class GroupWorkController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch (path) {
		case "/groupwork":
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			break;
		
		case "/groupwork-add":
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			break;
			
		case "/groupwork-details":
			req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}
