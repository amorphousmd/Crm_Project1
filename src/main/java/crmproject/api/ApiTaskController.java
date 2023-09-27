package crmproject.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crmproject.payload.response.BaseResponse;
import crmproject.service.TaskService;
import crmproject.service.TaskUserService;

@WebServlet(name = "apiTaskController", urlPatterns = {"/api/task/delete", 
													   "/api/task/modify",
													   "/api/task/modify-user-level",
													   "/api/task/add-users",
													   "/api/task/remove-users"})
public class ApiTaskController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TaskService taskService = new TaskService();
	private TaskUserService taskUserService = new TaskUserService();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcPath = req.getServletPath();
		switch(funcPath) {
		case "/api/task/delete":
		{
			int id = Integer.parseInt(req.getParameter("id"));
			
			boolean isSuccess = taskService.deleteRoleById(id);
			
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setStatusCode(200);
			baseResponse.setMessage(isSuccess ? "Success" : "Failed");
			baseResponse.setData(isSuccess);
			
			String dataJSON = gson.toJson(baseResponse);
			
			PrintWriter out = resp.getWriter();
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        
		    out.print(dataJSON);	
	        out.flush();   
			break;
		}
		case "/api/task/modify":
		{
			int id = Integer.parseInt(req.getParameter("id"));
			String modifiedTaskName = req.getParameter("taskName");
			String modifiedDescription = req.getParameter("taskDescription");
			String modifiedStartDate = req.getParameter("startDate");
			String modifiedEndDate = req.getParameter("endDate");
			String projectNum = req.getParameter("projectNum");
			String statusNum = req.getParameter("statusNum");
			int modifiedProjectNum = 1;
			try {
				modifiedProjectNum = Integer.parseInt(projectNum);
			} catch (NumberFormatException e) {
				modifiedProjectNum = 1;
			}
			int modifiedStatusNum = 1;
			try {
				modifiedStatusNum = Integer.parseInt(statusNum);
			} catch (NumberFormatException e) {
				modifiedStatusNum = 1;
			}
			
			boolean isSuccess = taskService.modifyUserById(	id, modifiedTaskName,
																modifiedDescription, modifiedStartDate, 
																modifiedEndDate, modifiedProjectNum, 
																modifiedStatusNum);
			
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setStatusCode(200);
			baseResponse.setMessage(isSuccess ? "Success" : "Failed");
			baseResponse.setData(isSuccess);
			
			String dataJSON = gson.toJson(baseResponse);
			
			PrintWriter out = resp.getWriter();
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        
		    out.print(dataJSON);	
	        out.flush();   
			break;
		}
		case "/api/task/modify-user-level":
		{
			int id = Integer.parseInt(req.getParameter("id"));
			String modifiedStartDate = req.getParameter("startDate");
			String modifiedEndDate = req.getParameter("endDate");
			String statusNum = req.getParameter("statusNum");
			int modifiedStatusNum = 1;
			try {
				modifiedStatusNum = Integer.parseInt(statusNum);
			} catch (NumberFormatException e) {
				modifiedStatusNum = 1;
			}
			
			boolean isSuccess = taskService.modifyUserByIdUserLevel(	id, modifiedStartDate, 
																		modifiedEndDate, modifiedStatusNum);
			
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setStatusCode(200);
			baseResponse.setMessage(isSuccess ? "Success" : "Failed");
			baseResponse.setData(isSuccess);
			
			String dataJSON = gson.toJson(baseResponse);
			
			PrintWriter out = resp.getWriter();
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        
		    out.print(dataJSON);	
	        out.flush();   
			break;
		}
		case "/api/task/add-users":
		{
			String selectedTaskIdStr = req.getParameter("task");
			int selectedTaskId = 0;
			try {
				selectedTaskId = Integer.parseInt(selectedTaskIdStr);
			} catch (NumberFormatException e) {
				selectedTaskId = 0;
			}
			
			String input = req.getParameter("user-list");
			String[] parts = input.split(",");
			int[] selectedUsers = new int[parts.length];

			for (int i = 0; i < parts.length; i++) {
				selectedUsers[i] = Integer.parseInt(parts[i]);
			}
			
			boolean isSuccess = false;
			for (int userId : selectedUsers) {
				System.out.println(selectedTaskId);
				System.out.println(userId);
			    isSuccess = taskUserService.addEntry(selectedTaskId, userId);
			}
			
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setStatusCode(200);
			baseResponse.setMessage(isSuccess ? "Success" : "Failed");
			baseResponse.setData(isSuccess);
			
			String dataJSON = gson.toJson(baseResponse);
			
			PrintWriter out = resp.getWriter();
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        
		    out.print(dataJSON);	
	        out.flush();   
			break;
		}
		case "/api/task/remove-users":
		{
			String selectedTaskIdStr = req.getParameter("task");
			int selectedTaskId = 0;
			try {
				selectedTaskId = Integer.parseInt(selectedTaskIdStr);
			} catch (NumberFormatException e) {
				selectedTaskId = 0;
			}
			
			String input = req.getParameter("user-list");
			String[] parts = input.split(",");
			int[] selectedUsers = new int[parts.length];

			for (int i = 0; i < parts.length; i++) {
				selectedUsers[i] = Integer.parseInt(parts[i]);
			}
			
			boolean isSuccess = false;
			for (int userId : selectedUsers) {
				System.out.println(selectedTaskId);
				System.out.println(userId);
			    isSuccess = taskUserService.removeEntry(selectedTaskId, userId);
			}
			
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setStatusCode(200);
			baseResponse.setMessage(isSuccess ? "Success" : "Failed");
			baseResponse.setData(isSuccess);
			
			String dataJSON = gson.toJson(baseResponse);
			
			PrintWriter out = resp.getWriter();
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        
		    out.print(dataJSON);	
	        out.flush();   
			break;
		}
		}
	}
}
