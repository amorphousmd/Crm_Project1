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

@WebServlet(name = "apiProjectController", urlPatterns = "/api/project/delete")
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
		}
	}

}
