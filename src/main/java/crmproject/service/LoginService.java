package crmproject.service;

import java.util.List;

//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crmproject.entity.NguoiDung;
import crmproject.repository.NguoiDungRepository;

public class LoginService {
	
	private NguoiDungRepository nguoiDungRepository = new NguoiDungRepository();
	
	public boolean checkLogin(String email, String password, HttpServletRequest request) {
		List<NguoiDung> listNguoiDung = nguoiDungRepository.findByEmailandPassword(email, password);
		if (listNguoiDung.size() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("roleName", listNguoiDung.get(0).getLoaiThanhVien().getTen());
		}
		
		return listNguoiDung.size() > 0;
	}
}
