package crmproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmproject.entity.CongViec;
import crmproject.entity.DuAn;
import crmproject.entity.NguoiDung;
import crmproject.service.ProjectService;
import crmproject.service.TaskService;
import crmproject.service.TaskUserService;
import crmproject.service.UserService;

@WebServlet(name = "taskController", urlPatterns = {"/task", "/task-add"})
public class TaskController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaskService taskService = new TaskService();
	private ProjectService projectService = new ProjectService();
	private UserService userService = new UserService();
	private TaskUserService taskUserService = new TaskUserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/task":
			List<CongViec> listCongViec = taskService.getTaskTable();
			req.setAttribute("taskList", listCongViec);
			
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			break;
		
		case "/task-add":
			List<DuAn> listDuAn = projectService.getProjectTable();
			req.setAttribute("projectList", listDuAn);
			
			List<NguoiDung> listNguoiDung = userService.getUserTable();
			req.setAttribute("userList", listNguoiDung);
			
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
			
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/task":
			List<CongViec> listCongViec = taskService.getTaskTable();
			req.setAttribute("taskList", listCongViec);
			
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			break;
		
		case "/task-add":
			int taskProjectId = 1;
			int taskUserId = 1;
			req.setCharacterEncoding("UTF-8");
			try {
	            taskProjectId = Integer.parseInt(req.getParameter("task-project-id"));
	        } catch (NumberFormatException e) {
	            System.out.println("Error: Could not parse the input. Please enter a valid integer.");
	        }
			
			try {
	            taskUserId = Integer.parseInt(req.getParameter("task-user-id"));
	        } catch (NumberFormatException e) {
	            System.out.println("Error: Could not parse the input. Please enter a valid integer.");
	        }
			
			String taskName = req.getParameter("task-name");
			String taskStartDate = req.getParameter("task-start-date");
			String taskEndDate = req.getParameter("task-end-date");
			String taskDescription = "Change later";
			
			boolean isSuccess = taskService.addTask(taskName, taskDescription, taskStartDate, taskEndDate, taskProjectId);
			if (isSuccess) {
				System.out.println("Insert Successful");
				boolean isSuccess2 = taskUserService.addEntry(taskService.getTaskLastestId(), taskUserId);
				if (isSuccess2) {
					System.out.println("Link task with user successful");
				}
				else {
					System.out.println("Link task with user failed");
				}
				req.setAttribute("isSuccess2", isSuccess);
			}
			else {
				System.out.println("Failed insert");
			}
			req.setAttribute("isSuccess", isSuccess);
			
			List<DuAn> listDuAn = projectService.getProjectTable();
			req.setAttribute("projectList", listDuAn);
			
			List<NguoiDung> listNguoiDung = userService.getUserTable();
			req.setAttribute("userList", listNguoiDung);
			
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
			
		default:
			break;
		}
	}
}
