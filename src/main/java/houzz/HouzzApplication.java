package houzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import houzz.service.CookiesService;
import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@Controller
@MapperScan(value = {"houzz"})
public class HouzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouzzApplication.class, args);
	}
	
	@Autowired
	CookiesService cookiesService;
	@RequestMapping("/")
	public String index(Model model,HttpServletRequest request) {
		cookiesService.executeMain(request, model);
		return "thymeleaf/index";
	}
	@Bean
	// 암호화 모듈
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
