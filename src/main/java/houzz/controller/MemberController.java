package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.MemberCommand;
import houzz.service.member.MemberNumService;

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
	@Autowired
	MemberNumService memberNumService;
	@RequestMapping(value = "memberRegist", method = RequestMethod.GET)
	public String memberRegist(MemberCommand memberCommand) {
		memberNumService.execute(memberCommand);
		return "thymeleaf/member/memberForm";
	}
	
	@RequestMapping(value = "memberRegist", method = RequestMethod.POST)
	public String memberRegist() {
		return "redirect:memberList";
	}
}
