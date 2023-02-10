package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.LoginCommand;
import houzz.service.LoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.sql.Result;

@Controller
public class LoginController {
	@ModelAttribute
	LoginCommand getLoginCommand() {
		return new LoginCommand();
	}

	@Autowired
	LoginService loginService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping("login")
	public String login() {
		return "thymeleaf/login";
	}

	@RequestMapping(value = "/login/loginPro", method = RequestMethod.GET)
	public String Home() {
		return "redirect:/";
	}

	@RequestMapping(value = "/login/loginPro", method = RequestMethod.POST)
	public String loginPro(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "thymeleaf/login";
		} 
			loginService.execute(loginCommand, session);
			return "redirect:/";
		}
	@RequestMapping("/login/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
