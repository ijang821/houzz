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
import houzz.domain.AuthInfoDTO;
import houzz.service.EmailCheckService;
import houzz.service.IdCheckService;
import houzz.service.member.MemberJoinService;
import houzz.service.member.MemberNumService;

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
	
	@Autowired
	MemberNumService memberNumService;
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String regist(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, MemberCommand memberCommand) {
		if (agree == false) {
			return "thymeleaf/register/agree";
		}
		memberNumService.execute(memberCommand);
		return "thymeleaf/memberShip/memberJoinForm";
	}

	@RequestMapping(value = "memberJoinAction", method = RequestMethod.GET)
	public String memberJoinAction() {
		return "redirect:/register/agree";
	}
	
	@Autowired
	IdCheckService idcheckService;
	@Autowired
	EmailCheckService emailCheckService ;
	@RequestMapping(value = "memberJoinAction", method = RequestMethod.POST)
	public String memberJoinAction1(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
		if (result.hasErrors()) {
		return "thymeleaf/memberShip/memberJoinForm";
		}
		if (!memberCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("memberPw", "memberCommand.memberPw", "비밀번호와 비밀번호 확인 다릅니다.");
		return "thymeleaf/memberShip/memberJoinForm";
		}
		String i = idcheckService.execute(memberCommand.getMemberId());
		if(i != null) {
			result.rejectValue("memberId", "memberCommand.memberId", 
					"중복 아이디입니다.");
			return "thymeleaf/memberShip/memberJoinForm";
		}
		AuthInfoDTO authInfo = emailCheckService.execute(memberCommand.getMemberEmail());
		if(authInfo != null) {
			result.rejectValue("memberEmail", "memberCommand.memberEmail", 
					"중복 Email입니다.");
			return "thymeleaf/memberShip/memberJoinForm";
		}
		memberJoinService.execute(memberCommand,model);
		return "thymeleaf/memberShip/welcomeMem";
	}
	
}