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

@WebServlet(name = "groupWorkController", urlPatterns = {"/groupwork", 
														 "/groupwork-add", 
														 "/groupwork-details",
														 "/groupwork-assign"})
public class GroupWorkController extends HttpServlet{
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
		
		case "/groupwork":
		{
			List<DuAn> listDuAn = projectService.getProjectTable();
			req.setAttribute("projectList", listDuAn);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			break;
		}
		
		case "/groupwork-add":
		{
			List<NguoiDung> listNguoiDung = userService.getUserTable();
			req.setAttribute("userList", listNguoiDung);
			
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			break;
		}
		
		case "/groupwork-details":
		{
			List<DuAn> listDuAn = projectService.getProjectTable();

			List<List<DuAn>> listDuAnSorted = projectService.getProjectTableSorted();
			List<DuAn> listDuAnInProgress = listDuAnSorted.get(0);
			List<DuAn> listDuAnFinished = listDuAnSorted.get(1);
			List<DuAn> listDuAnNotStarted = listDuAnSorted.get(2);
			
			req.setAttribute("projectList", listDuAn);
			req.setAttribute("listProjectInProgress", listDuAnInProgress);
			req.setAttribute("listProjectFinished", listDuAnFinished);
			req.setAttribute("listProjectNotStarted", listDuAnNotStarted);
			
			req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
			break;
		}	
		case "/groupwork-assign":
		{
			List<DuAn> listDuAn = projectService.getProjectTable();
			req.setAttribute("projectList", listDuAn);
			
			List<NguoiDung> listNguoiDung = userService.getUserTable();
			req.setAttribute("userList", listNguoiDung);
			
			req.getRequestDispatcher("groupwork-assign.jsp").forward(req, resp);
			break;
		}
		
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/groupwork":
		{
			List<DuAn> listDuAn = projectService.getProjectTable();
			req.setAttribute("projectList", listDuAn);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			break;
		}
		case "/groupwork-add":
		{
			req.setCharacterEncoding("UTF-8");
			
			List<NguoiDung> listNguoiDung = userService.getUserTable();
			req.setAttribute("userList", listNguoiDung);
			
			String projectName = req.getParameter("project-name");
			String projectDescription = req.getParameter("project-description");
			String projectStartDate = req.getParameter("project-start-date");
			String projectEndDate = req.getParameter("project-end-date");
			int idManager = 1;
			String projectState = req.getParameter("selected-state");
			
			boolean isSuccess = projectService.addProject(	projectName, projectDescription, 
															projectStartDate, projectEndDate, 
															idManager, projectState);
			
			if (isSuccess) {
				System.out.println("Insert Successful");
			}
			else {
				System.out.println("Failed insert");
			}
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			break;
		}	
		case "/groupwork-details":
			req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
			break;
		case "/groupwork-assign":
		{
			System.out.println(req.getParameter("project-id"));
			System.out.println("dsds");
			System.out.println(req.getParameter("project-id-users"));
			
			List<DuAn> listDuAn = projectService.getProjectTable();
			req.setAttribute("projectList", listDuAn);
			
			List<NguoiDung> listNguoiDung = userService.getUserTable();
			req.setAttribute("userList", listNguoiDung);
			req.getRequestDispatcher("groupwork-assign.jsp").forward(req, resp);
			break;
		}
		default:
			break;
		}
	}
}
