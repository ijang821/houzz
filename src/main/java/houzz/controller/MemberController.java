package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.MemberCommand;
import houzz.service.member.MemberDetailService;
import houzz.service.member.MemberListController;
import houzz.service.member.MemberNumService;
import houzz.service.member.MemberRegistService;

@Controller
@RequestMapping("member")
public class MemberController {
	/**
	 * 회원 초기 페이지
	 * @return
	 */
	@Autowired
	MemberListController memberListController;
	@RequestMapping("memberList")
	public String memberList(Model model) {
		memberListController.execute(model);
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
	/**
	 * 회원 등록
	 */
	@Autowired
	MemberRegistService memberRegistService;
	@RequestMapping(value = "memberRegist", method = RequestMethod.POST)
	public String memberWrite(@Validated MemberCommand memberCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		if(!memberCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("memberPw", "memberCommand.memberPw",
					"비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/member/memberForm";
		}
		Integer i = memberRegistService.execute(memberCommand);
		if(i!=null) {
			return "redirect:memberList";
		}
		else return "thymeleaf/member/memberForm";
	}
	@Autowired
	MemberDetailService memberDetailService;
	@RequestMapping(value = "memberDetail/{memberNum}")
	public String memberDetail(@PathVariable(value = "memberNum")String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberDetail";
	}
}
