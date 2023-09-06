package crmproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmproject.entity.NguoiDung;
import crmproject.service.UserService;

@WebServlet(name = "userController", urlPatterns = {"/user-table", "/user-add", "/user-details"})
public class UserController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/user-add":
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		
		case "/user-details":
			req.getRequestDispatcher("user-details.jsp").forward(req, resp);
			break;
		
		case "/user-table":
			List<NguoiDung> listNguoiDung = userService.getUserTable();
			req.setAttribute("userList", listNguoiDung);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
			
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/user-add":
			req.setCharacterEncoding("UTF-8");
			String userEmail = req.getParameter("user-email");
			String userPassword = req.getParameter("user-password");
			String userFullname = req.getParameter("user-fullname");
			String userLocation = req.getParameter("user-selected-location");
			String userPhone = req.getParameter("user-phone");
			int UserIdRole = 1;
			boolean isSuccess = userService.addUser(userEmail, userPassword, 
													userFullname, userLocation, 
													userPhone, UserIdRole);
			if (isSuccess) {
				System.out.println("Insert Successful");
			}
			else {
				System.out.println("Failed insert");
			}
			req.setAttribute("isSuccess", isSuccess);
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
