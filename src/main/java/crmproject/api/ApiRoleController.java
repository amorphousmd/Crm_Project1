package crmproject.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crmproject.entity.LoaiThanhVien;
import crmproject.payload.response.BaseResponse;
import crmproject.service.RoleService;

@WebServlet(name = "apiRoleController", urlPatterns = {"/api/role/delete"})
public class ApiRoleController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		
		boolean isSuccess = roleService.deleteRoleById(id);
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatuseCode(200);
		baseResponse.setMessage(isSuccess ? "Success" : "Failed");
		baseResponse.setData(isSuccess);
		
		String dataJSON = gson.toJson(baseResponse);

//		String json = "{\"id\":2,\"ten\":\"leader\",\"mota\":\"test dữ liệu\"}";
		
//		LoaiThanhVien loaiThanhVien = gson.fromJson(json, LoaiThanhVien.class);
		
//		LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
//		loaiThanhVien.setId(2);
//		loaiThanhVien.setTen("leader");
//		loaiThanhVien.setMota("test du liệu");
		
//		String dataJSON = gson.toJson(baseResponse);
		
		PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
	    out.print(dataJSON);	
        out.flush();   
	}
}
