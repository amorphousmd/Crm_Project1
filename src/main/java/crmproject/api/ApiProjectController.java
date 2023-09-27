package crmproject.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crmproject.entity.DuAn;
import crmproject.payload.response.BaseResponse;
import crmproject.service.ProjectService;
import crmproject.service.ProjectUserService;

@WebServlet(name = "apiProjectController", urlPatterns = {"/api/project/delete", 
														  "/api/project/modify",
														  "/api/project/modify-user-level",
														  "/api/project/getall",
														  "/api/project/add-users",
														  "/api/project/remove-users"})
public class ApiProjectController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private ProjectService projectService = new ProjectService();
	private ProjectUserService projectUserService = new ProjectUserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcPath = req.getServletPath();
		switch(funcPath) {
		case "/api/project/delete":
		{
			int id = Integer.parseInt(req.getParameter("id"));
			
			boolean isSuccess = projectService.deleteRoleById(id);
			
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
		case "/api/project/modify":
		{
			int id = Integer.parseInt(req.getParameter("id"));
			String modifiedProjectName = req.getParameter("projectName");
			String modifiedDescription = req.getParameter("projectDescription");
			String modifiedStartDate = req.getParameter("startDate");
			String modifiedEndDate = req.getParameter("endDate");
			String managerNum = req.getParameter("managerNum");
			String statusNum = req.getParameter("statusNum");
			int modifiedManagerNum = 1;
			try {
				modifiedManagerNum = Integer.parseInt(managerNum);
			} catch (NumberFormatException e) {
				modifiedManagerNum = 1;
			}
			int modifiedStatusNum = 1;
			try {
				modifiedStatusNum = Integer.parseInt(statusNum);
			} catch (NumberFormatException e) {
				modifiedStatusNum = 1;
			}
			
			boolean isSuccess = projectService.modifyUserById(	id, modifiedProjectName,
																modifiedDescription, modifiedStartDate, 
																modifiedEndDate, modifiedManagerNum, 
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
		case "/api/project/modify-user-level":
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
			
			boolean isSuccess = projectService.modifyUserByIdUserLevel(	id, modifiedStartDate, 
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
		case "/api/project/getall":
		{
			int[] stateArray = {0, 0, 0};
			List<DuAn> listDuAn = projectService.getProjectTable();
			for(DuAn duAn: listDuAn)
			{
				int key = duAn.getTrangThai().getId();
				switch (key) {
				case 1:
					stateArray[0]++;
					break;
				case 2:
					stateArray[1]++;
					break;
				case 3:
					stateArray[2]++;
					break;
				default:
					break;
				}
			}
			
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setStatusCode(200);
			baseResponse.setMessage("Success");
			baseResponse.setData(stateArray);
			
			String dataJSON = gson.toJson(baseResponse);
			
			PrintWriter out = resp.getWriter();
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        
		    out.print(dataJSON);	
	        out.flush();   
			break;
		}
		case "/api/project/add-users":
		{
			String selectedProjectIdStr = req.getParameter("project");
			int selectedProjectId = 0;
			try {
				selectedProjectId = Integer.parseInt(selectedProjectIdStr);
			} catch (NumberFormatException e) {
				selectedProjectId = 0;
			}
			
			String input = req.getParameter("user-list");
			String[] parts = input.split(",");
			int[] selectedUsers = new int[parts.length];

			for (int i = 0; i < parts.length; i++) {
				selectedUsers[i] = Integer.parseInt(parts[i]);
			}
			
			boolean isSuccess = false;
			for (int userId : selectedUsers) {
				System.out.println(selectedProjectId);
				System.out.println(userId);
			    isSuccess = projectUserService.addEntry(selectedProjectId, userId);
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
		case "/api/project/remove-users":
		{
			String selectedProjectIdStr = req.getParameter("project");
			int selectedProjectId = 0;
			try {
				selectedProjectId = Integer.parseInt(selectedProjectIdStr);
			} catch (NumberFormatException e) {
				selectedProjectId = 0;
			}
			
			String input = req.getParameter("user-list");
			String[] parts = input.split(",");
			int[] selectedUsers = new int[parts.length];

			for (int i = 0; i < parts.length; i++) {
				selectedUsers[i] = Integer.parseInt(parts[i]);
			}
			
			boolean isSuccess = false;
			for (int userId : selectedUsers) {
				System.out.println(selectedProjectId);
				System.out.println(userId);
			    isSuccess = projectUserService.removeEntry(selectedProjectId, userId);
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
