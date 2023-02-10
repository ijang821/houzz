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
	
	public void execute(LoginCommand loginCommand, HttpSession session) {
		AuthInfoDTO authInfoDTO = loginMapper.loginselect(loginCommand.getUserId()); // 사용자 정보 가져옴
		// session 생성
		session.setAttribute("authInfoDTO", authInfoDTO);
		System.out.println("session");
		
	}
}

