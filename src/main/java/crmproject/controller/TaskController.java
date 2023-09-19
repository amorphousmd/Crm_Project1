package crmproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmproject.entity.CongViec;
import crmproject.service.TaskService;

@WebServlet(name = "taskController", urlPatterns = {"/task", "/task-add"})
public class TaskController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch (path) {
		case "/task":
			List<CongViec> listCongViec = taskService.getTaskTable();
			req.setAttribute("taskList", listCongViec);
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			break;
		
		case "/task-add":
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
			
		default:
			break;
		}
	}
}
