package houzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("member")
public class MemberController {
	@RequestMapping("memberList")
	public String memberList() {
		return "thymeleaf/member/memberList";
	}
	@RequestMapping(value = "memberRegist", method = RequestMethod.GET)
	public String memberRegist() {
		return "thmyeleaf/member/memberForm";
	}
}
