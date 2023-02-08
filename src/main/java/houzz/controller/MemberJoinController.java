package houzz.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("register")

public class MemberJoinController {
	@RequestMapping("agree")
	public String agree1() {
		return "thymeleaf/register/agree";
	}

	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String agree() {
		return "thymeleaf/register/agree";
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String regist(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
		if(agree == false) {
			return "thymeleaf/register/agree";
		}
		return "thymeleaf/membership/memberJoinForm";
	}

	@RequestMapping(value = "memberJoinAction", method = RequestMethod.POST)
	public String memberJoinAction(
			@RequestParam(value = "memberName")String memberName) {
		return "thymeleaf/membership/welcomeMem";
	}
}