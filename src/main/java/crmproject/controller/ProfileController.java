package crmproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmproject.entity.DuAn;
import crmproject.entity.NguoiDung;
import crmproject.service.ProjectService;
import crmproject.service.UserService;
import crmproject.tools.DateConversion;

@WebServlet(name = "profileController", urlPatterns = {"/profile", "/profile-edit"})
public class ProfileController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectService projectService = new ProjectService();
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/profile":
			int id;
			try {
			    id = Integer.parseInt("5");
			} catch (NumberFormatException e) {
				req.getRequestDispatcher("user-details.jsp").forward(req, resp);
				break;
			}
			
			List<DuAn> listDuAn = projectService.getProjectsWithUserId(id);
			
			List<List<DuAn>> listDuAnSorted = projectService.getSortedProjectsWithUserId(id);
			List<DuAn> listDuAnInProgress = listDuAnSorted.get(0);
			List<DuAn> listDuAnFinished = listDuAnSorted.get(1);
			List<DuAn> listDuAnNotStarted = listDuAnSorted.get(2);
			
			req.setAttribute("projectList", listDuAn);
			req.setAttribute("listProjectInProgress", listDuAnInProgress);
			req.setAttribute("listProjectFinished", listDuAnFinished);
			req.setAttribute("listProjectNotStarted", listDuAnNotStarted);
			
			NguoiDung nguoiDung = userService.getUserAtId(id);
			System.out.println(nguoiDung.getFullname());
			req.setAttribute("user", nguoiDung);
			
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
			int id;
			try {
			    id = Integer.parseInt("1");
			} catch (NumberFormatException e) {
				req.getRequestDispatcher("user-details.jsp").forward(req, resp);
				break;
			}
			
			List<DuAn> listDuAn = projectService.getProjectsWithUserId(id);
			
			List<List<DuAn>> listDuAnSorted = projectService.getSortedProjectsWithUserId(id);
			List<DuAn> listDuAnInProgress = listDuAnSorted.get(0);
			List<DuAn> listDuAnFinished = listDuAnSorted.get(1);
			List<DuAn> listDuAnNotStarted = listDuAnSorted.get(2);
			
			req.setAttribute("projectList", listDuAn);
			req.setAttribute("listProjectInProgress", listDuAnInProgress);
			req.setAttribute("listProjectFinished", listDuAnFinished);
			req.setAttribute("listProjectNotStarted", listDuAnNotStarted);
			
			NguoiDung nguoiDung = userService.getUserAtId(id);
			System.out.println(nguoiDung.getFullname());
			req.setAttribute("user", nguoiDung);
			
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			break;
		
		case "/profile-edit":
			req.setCharacterEncoding("UTF-8");
			String projectName = req.getParameter("project-name");
			String projectDescription = req.getParameter("project-description");
			String projectStartDate = DateConversion.convertDate(req.getParameter("project-start-date"));
			String projectEndDate = DateConversion.convertDate(req.getParameter("project-end-date"));
			int projectIdManager = 1;
			try {
				projectIdManager = Integer.parseInt(req.getParameter("project-id-manager"));
	        } catch (NumberFormatException e) {
	            System.out.println("Error: Could not parse the input. Please enter a valid integer.");
	        }
			String projectState = req.getParameter("selected-state");
			
			boolean isSuccess = projectService.addProject(	projectName, projectDescription, 
															projectStartDate, projectEndDate, 
															projectIdManager, projectState);
			
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
