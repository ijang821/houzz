package houzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import houzz.command.LoginCommand;
import houzz.domain.AuthInfoDTO;
import houzz.mapper.LoginMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	@Autowired
	LoginMapper loginMapper;
	
	public String execute(LoginCommand loginCommand, HttpSession session) {
		AuthInfoDTO authInfoDTO =(AuthInfoDTO) session.getAttribute("authInfoDTO");
		session.setAttribute("authInfoDTO", authInfoDTO);
		authInfoDTO = loginMapper.loginselect(loginCommand.getUserId());
		System.out.println("session");
		return "thymeleaf/index";

	}
}

