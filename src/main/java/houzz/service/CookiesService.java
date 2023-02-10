package houzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.LoginCommand;
import houzz.domain.AuthInfoDTO;
import houzz.mapper.LoginMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CookiesService {
	@Autowired
	LoginMapper loginMapper;
	public void executeMain(HttpServletRequest request, Model model) {
		Cookie [] cookies = request.getCookies();
		if(cookies != null && cookies.length >0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().startsWith("id")) {	
					LoginCommand loginCommand = new LoginCommand();
					loginCommand.setUserId(cookie.getValue());
					loginCommand.setIdStore(true);
					model.addAttribute("loginCommand", loginCommand);
				}
				if(cookie.getName().startsWith("auto")) {
					System.out.println("bxbxdbxbcxbxcbxbx");
					AuthInfoDTO authInfo = loginMapper.loginselect(cookie.getValue()); 
					HttpSession session = request.getSession();
					session.setAttribute("authInfoDTO", authInfo);		
				}
			}
		}
	}

}
