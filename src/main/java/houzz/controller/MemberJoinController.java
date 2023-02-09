package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import houzz.command.MemberCommand;
import houzz.service.member.MemberJoinService;

@Controller
@RequestMapping("register")

public class MemberJoinController {
	@ModelAttribute
	MemberCommand getMemberCommand() {
		return new MemberCommand();
	}

	@Autowired
	MemberJoinService memberJoinService;
	
	@RequestMapping("agree")
	public String agree1() {
		return "thymeleaf/register/agreeCk";
	}
	

	@RequestMapping(value = "memberType", method = RequestMethod.GET)
	public String agree() {
		return "thymeleaf/register/memagree";
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String regist(@RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
		if (agree == false) {
			return "thymeleaf/register/agree";
		}
		return "thymeleaf/membership/memberJoinForm";
	}

	@RequestMapping(value = "memberJoinAction", method = RequestMethod.GET)
	public String memberJoinAction() {
		return "redirect:/register/agree";
	}

	@RequestMapping(value = "memberJoinAction", method = RequestMethod.POST)
	public String memberJoinAction1(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
		if (result.hasErrors()) {
		return "thymeleaf/membership/memberJoinForm";
		}
		if (!memberCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("memberPw", "memberCommand.memberPw", "비밀번호와 비밀번호 확인 다릅니다.");
		return "thymeleaf/membership/memberJoinForm";
		}
		memberJoinService.execute(memberCommand,model);
		return "thymeleaf/membership/welcomeMem";
	}
	
}