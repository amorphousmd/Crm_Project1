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
import crmproject.service.UserService;

@WebServlet(name = "apiUserController", urlPatterns = {"/api/user/delete", "/api/user/modify"})
public class ApiUserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/api/user/delete":
		{
			int id = Integer.parseInt(req.getParameter("id"));
			
			boolean isSuccess = userService.deleteUserById(id);
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Success" : "Failed");
			response.setData(isSuccess);
			
			String jsonData = gson.toJson(response);
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        
	        writer.print(jsonData);
	        writer.flush();
	        
			break;
		}
		case "/api/user/modify":
		{
			int id = Integer.parseInt(req.getParameter("id"));
			String modifiedFullname = req.getParameter("fullname");
			String modifiedEmail = req.getParameter("email");
			String modifiedLocation = req.getParameter("location");
			String modifiedPhone = req.getParameter("phone");
			String roleNum = req.getParameter("roleNum");
			int modifiedRoleNum = 1;
			try {
			    modifiedRoleNum = Integer.parseInt(roleNum);
			} catch (NumberFormatException e) {
			    modifiedRoleNum = 1;
			}

			
			boolean isSuccess = userService.modifyUserById(	id, modifiedEmail,
															modifiedFullname, modifiedLocation, 
															modifiedPhone, modifiedRoleNum);
			
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
			
		default:
			break;
		}
	}
}
