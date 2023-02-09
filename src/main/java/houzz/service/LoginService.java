package houzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.LoginCommand;
import houzz.domain.AuthInfoDTO;
import houzz.mapper.LoginMapper;

@Service
public class LoginService {
	@Autowired
	LoginMapper loginMapper;
    public String execute(LoginCommand loginCommand) {
		String path = "thymeleaf/index";
		AuthInfoDTO authInfoDTO = loginMapper.loginselect(loginCommand.getUserId());
		System.out.println("로그인 되었습니다.");
		return path;
		
		
	}
}
