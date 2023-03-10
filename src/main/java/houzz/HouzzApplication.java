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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import houzz.service.CookiesService;
import houzz.service.estate.EstateListController;
import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@Controller
@MapperScan(value = {"houzz"})
public class HouzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouzzApplication.class, args);
	}
	@Autowired
	EstateListController estateListController;
	@Autowired
	CookiesService cookiesService;
	@RequestMapping("/")
	public String index(@RequestParam(value = "estateWord", required = false)String estateWord, Model model,HttpServletRequest request) {
		cookiesService.executeMain(request, model);
		estateListController.execute(estateWord, model);
		return "thymeleaf/index";
	}
	@Bean
	// 암호화 모듈
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(value = "jsonView")
    public MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }
}
