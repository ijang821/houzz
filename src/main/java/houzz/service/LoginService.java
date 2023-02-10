package houzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import houzz.command.LoginCommand;
import houzz.domain.AuthInfoDTO;
import houzz.mapper.LoginMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	PasswordEncoder passwordEncoder;

	public String execute(LoginCommand loginCommand, BindingResult result, HttpSession session,
			HttpServletResponse response) {
		String path = "thymeleaf/index";
		AuthInfoDTO authInfoDTO = loginMapper.loginselect(loginCommand.getUserId()); // 사용자 정보 가져옴

		if (authInfoDTO != null) {
			if (!passwordEncoder.matches(loginCommand.getUserPw(), authInfoDTO.getUserPw())) {
				result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 틀렸습니다.");
			} else {
				System.out.println("session");
				session.setAttribute("authInfoDTO", authInfoDTO); // session 생성
				if (loginCommand.getIdStore() != null && loginCommand.getIdStore()) {
					Cookie cookie = new Cookie("idStore", authInfoDTO.getUserId());
					cookie.setPath("/");
					cookie.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("idStore", "");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				if (loginCommand.getAutoLogin() != null && loginCommand.getAutoLogin()) {
					Cookie cookie = new Cookie("autoLogin", authInfoDTO.getUserId());
					cookie.setPath("/");
					cookie.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(cookie);  // 사용자의 웹브라우저에 전달
				}
				//else {
				//	result.rejectValue("userId", "loginCommand.userId",
				//			"아이디가 존재하지 않습니다.");
				//}
			}
		}
		return path;
	}
}