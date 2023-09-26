package crmproject.filter;

import java.io.IOException;
//import java.net.URLDecoder;
//import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "customFilter", urlPatterns = {"/role-add", "/role-table", 
													   "/groupwork", "/user-table", 
													   "/task"})
public class CustomFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest)request).getServletPath();
		String contextPath = ((HttpServletRequest)request).getContextPath();
		
		// Implemented using cookies
//		String roleName = "";
//		Cookie[] cookies = ((HttpServletRequest)request).getCookies();
//		
//		for (Cookie cookie : cookies) 
//		{ 
//		    if (cookie.getName().equals("adminCookie")) 
//		    {
//		    	roleName = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.toString());   	
//		    }
//		}
//		
//		switch (path) {
//		case "/role-add":
//			if (roleName != null && roleName.toUpperCase().equals("ADMIN")) {
//				chain.doFilter(request, response);
//			}
//			else {
//				((HttpServletResponse)response).sendRedirect(contextPath + "/");
//			}
//			break;
//			
//		default:
//			break;
//		}
		
		// Implemented using session
		HttpSession session = ((HttpServletRequest)request).getSession();
		String roleName = (String)session.getAttribute("roleName");
		
		switch (path) {
		case "/role-add":
		{
			if (roleName != null && ( roleName.toUpperCase().equals("ADMIN") )) {
				chain.doFilter(request, response);
			}
			else {
				((HttpServletResponse)response).sendRedirect(contextPath + "/");
			}
			break;
		}
		
		case "/role-table":
		{
			if (roleName != null && ( roleName.toUpperCase().equals("ADMIN") )) {
				chain.doFilter(request, response);
			}
			else {
				((HttpServletResponse)response).sendRedirect(contextPath + "/");
			}
			break;
		}
		
		case "/groupwork":
		{
			if (roleName != null && ( roleName.toUpperCase().equals("ADMIN") ||
									  roleName.toUpperCase().equals("LEADER"))) {
				chain.doFilter(request, response);
			}
			else {
				((HttpServletResponse)response).sendRedirect(contextPath + "/");
			}
			break;
		}
		
		case "/user-table":
		{
			if (roleName != null && ( roleName.toUpperCase().equals("ADMIN") ||
									  roleName.toUpperCase().equals("LEADER"))) {
				chain.doFilter(request, response);
			}
			else {
				((HttpServletResponse)response).sendRedirect(contextPath + "/");
			}
			break;
		}
		
		case "/task":
		{
			if (roleName != null && ( roleName.toUpperCase().equals("ADMIN") ||
									  roleName.toUpperCase().equals("LEADER"))) {
				chain.doFilter(request, response);
			}
			else {
				((HttpServletResponse)response).sendRedirect(contextPath + "/");
			}
			break;
		}
		default:
			break;
		}
	}
}
