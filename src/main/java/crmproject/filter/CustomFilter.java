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

@WebFilter(filterName = "customFilter", urlPatterns = {"/role-add"})
public class CustomFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String path = ((HttpServletRequest)request).getServletPath();
		HttpSession session = ((HttpServletRequest)request).getSession();
		String roleName = (String)session.getAttribute("roleName");
		String contextPath = ((HttpServletRequest)request).getContextPath();
		
		switch (path) {
		case "/role-add":
			if (roleName != null && roleName.toUpperCase().equals("ADMIN")) {
				chain.doFilter(request, response);
			}
			else {
				((HttpServletResponse)response).sendRedirect(contextPath + "/");
			}
			break;

		default:
			break;
		}
		
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
		
		
	}
}
