package houzz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import houzz.command.LoginCommand;
import houzz.domain.AuthInfoDTO;
import houzz.mapper.LoginMapper;
import houzz.service.CookiesService;
import houzz.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
	@Autowired
	CookiesService cookiesService;
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		cookiesService.executeMain(request, model);
		return "thymeleaf/login";
	}

	@RequestMapping(value = "/login/loginPro", method = RequestMethod.GET)
	public String Home() {
		return "redirect:/";
	}

	@RequestMapping(value = "/login/loginPro", method = RequestMethod.POST)
	public String loginPro(@Validated LoginCommand loginCommand, BindingResult result, 
			HttpSession session,HttpServletResponse response) {	
		loginService.execute(loginCommand, result,session, response);
		if (result.hasErrors()) {
			return "thymeleaf/login";
		}
		return "redirect:/";
	}

	@RequestMapping("/login/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		Cookie cookie = new Cookie("autoLogin", "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/login/item.login", method = RequestMethod.GET)
	public String item(LoginCommand loginCommand) {
		return "thymeleaf/loginItem";
	}

	@RequestMapping(value = "/login/item.login", method = RequestMethod.POST)
	public String item(@Validated LoginCommand loginCommand, BindingResult result,
			HttpSession session, HttpServletResponse response) {	
		loginService.execute(loginCommand, result,session,response);
		if(result.hasErrors()) {
			return "thymeleaf/loginItem";
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = "<script language='javascript'>"
				   + " opener.location.reload();"
				   + " window.self.close();"
				   + "</script>";
		out.print(str);
		out.close();
		return null;
	}
	//인증서를 이용한 로그인
	@Autowired
	LoginMapper loginMapper;
	@RequestMapping(value = "/login/privateLogin", method = RequestMethod.POST)
	public @ResponseBody String privateLogin(
			@RequestParam(value = "address")String address,HttpSession session) {
		AuthInfoDTO authInfoDTO = loginMapper.addressLogin(address);
		if(authInfoDTO !=null) {
			session.setAttribute("authInfoDTO", authInfoDTO);
			return "1";
		}
		else {
			return "0";
		}
	}
	
	
}
