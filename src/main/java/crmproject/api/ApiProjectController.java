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
import crmproject.service.ProjectService;

@WebServlet(name = "apiProjectController", urlPatterns = {"/api/project/delete", "/api/project/modify"})
public class ApiProjectController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private ProjectService projectService = new ProjectService();
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
		}
	}

}
