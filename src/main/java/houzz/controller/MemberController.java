package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import houzz.command.MemberCommand;
import houzz.service.member.MemberDeleteService;
import houzz.service.member.MemberDelsService;
import houzz.service.member.MemberDetailService;
import houzz.service.member.MemberListController;
import houzz.service.member.MemberModifyService;
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
	public String memberList(@RequestParam(value = "memberWord" ,required = false ) String memberWord, Model model) {
		memberListController.execute(memberWord, model);
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
	 * @param memberCommand
	 * @param result
	 * @return
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
	
	/**
	 * 회원 정보 상세보기
	 * @param memberNum
	 * @param model
	 * @return
	 */
	@Autowired
	MemberDetailService memberDetailService;
	@RequestMapping(value = "memberDetail/{memberNum}")
	public String memberDetail(@PathVariable(value = "memberNum")String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberDetail";
	}
	/**
	 * 회원 정보 수정 페이지
	 * @param memberNum
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "memberModify", method = RequestMethod.GET)
	public String memberModify(@RequestParam(value = "memberNum")String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberUpdate";
	}
	/**
	 * 회원 정보 수정
	 * @param memberCommand
	 * @param result
	 * @return
	 */
	@Autowired
	MemberModifyService memberModifyService;
	@RequestMapping(value = "memberModify", method = RequestMethod.POST)
	public String memberModify(@Validated MemberCommand memberCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memberUpdate";
		}
		memberModifyService.execute(memberCommand);
		return "redirect:memberDetail/"+memberCommand.getMemberNum();
	}
	/**
	 * 회원 정보 삭제
	 * @param memberNum
	 * @return
	 */
	@Autowired
	MemberDeleteService memberDeleteService;
	@RequestMapping("memberDelete")
	public String memberDelete(@RequestParam("memberNum")String memberNum) {
		Integer i = memberDeleteService.execute(memberNum);
		if(i==0) {
			return "redirect:memberDetail/"+memberNum;
		}
		return "redirect:memberList";
	}
	/**
	 * 회원 선택 삭제
	 * @param memDels
	 * @return
	 */
	@Autowired
	MemberDelsService memberDelsService;
	@RequestMapping("memberDels")
	public String memberDels(@RequestParam("memDels")String memDels[]) {
		memberDelsService.execute(memDels);
		return "redirect:memberList";
	}
	
}
