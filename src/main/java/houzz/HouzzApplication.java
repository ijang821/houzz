package houzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@Controller
@MapperScan(value = {"houzz"})
public class HouzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouzzApplication.class, args);
	}
	
	
	@RequestMapping("/")
	public String index(Model model,HttpServletRequest request) {

		return "thymeleaf/index";
	}

}
