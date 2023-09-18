package crmproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmproject.service.ProjectService;
import crmproject.tools.DateConversion;

@WebServlet(name = "profileController", urlPatterns = {"/profile", "/profile-edit"})
public class ProfileController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectService profileService = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/profile":
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			break;
		
		case "/profile-edit":
			req.setCharacterEncoding("UTF-8");
			String projectName = req.getParameter("project-name");
			String projectDescription = req.getParameter("project-description");
			String projectStartDate = DateConversion.convertDate(req.getParameter("project-start-date"));
			String projectEndDate = DateConversion.convertDate(req.getParameter("project-end-date"));
			int idManager = 1;
			String projectState = req.getParameter("selected-state");
			
			boolean isSuccess = profileService.addProject(	projectName, projectDescription, 
															projectStartDate, projectEndDate, 
															idManager, projectState);
			
			if (isSuccess) {
				System.out.println("Insert Successful");
			}
			else {
				System.out.println("Failed insert");
			}
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
			break;
			
		default:
			break;
		}
	}
}
