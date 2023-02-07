package houzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.MemberCommand;

@Controller
@RequestMapping("member")
public class MemberController {
	/**
	 * 회원 초기 페이지
	 * @return
	 */
	@RequestMapping("memberList")
	public String memberList() {
		return "thymeleaf/member/memberList";
	}
	
	/**
	 * 회원 등록
	 * @return
	 */
	@RequestMapping(value = "memberRegist", method = RequestMethod.GET)
	public String memberRegist(MemberCommand memberCommand) {
		return "thymeleaf/member/memberForm";
	}
}
