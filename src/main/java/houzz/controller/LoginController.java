package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.LoginCommand;
import houzz.service.LoginService;

@Controller
public class LoginController {
	//@Autowired
	//LoginCommand loginCommand;
    @Autowired
    LoginService loginService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @RequestMapping("login")
	public String login() {
		return "thymeleaf/login/login";
	}
    
	@RequestMapping(value = "/login/loginPro", method = RequestMethod.GET)
	public String Home() {
		return "redirect:/";
	}

	@RequestMapping(value = "/login/loginPro", method = RequestMethod.POST)
	public String loginPro(LoginCommand loginCommand) {
		String path = loginService.execute(loginCommand);
		return path;
	}
	
}
