package crmproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmproject.entity.LoaiThanhVien;
import crmproject.service.QuyenService;
import crmproject.service.RoleTableService;

@WebServlet(name = "quyenController", urlPatterns = {"/role-add", "/role-table"})
public class RoleController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuyenService quyenService = new QuyenService();
	private RoleTableService roleTableService = new RoleTableService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String funcPath = req.getServletPath();
		switch(funcPath) {
		case "/role-add":
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		
		case "/role-table":
			List<LoaiThanhVien> listLoaiThanhVien = roleTableService.getRoleTable();
			req.setAttribute("roleList", listLoaiThanhVien);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rolename = req.getParameter("rolename");
		String description = req.getParameter("description");
		
		boolean isSuccess = quyenService.addRole(rolename, description);
		
		if (isSuccess) {
			System.out.println("Insert Successful");
		}
		else {
			System.out.println("Failed insert");
		}
		
		req.setAttribute("isSuccess", isSuccess);
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
}
