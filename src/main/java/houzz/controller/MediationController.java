package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import houzz.command.MediationCommand;
import houzz.command.MemberCommand;
import houzz.service.mediation.MediationDeleteService;
import houzz.service.mediation.MediationDelsService;
import houzz.service.mediation.MediationDetailService;
import houzz.service.mediation.MediationListService;
import houzz.service.mediation.MediationModifyService;
import houzz.service.mediation.MediationService;
import houzz.service.member.MemberNumService;

@Controller
@RequestMapping("register")
public class MediationController {
	@ModelAttribute
    MediationCommand getMediationCommand() {
		return new MediationCommand();
	}
   
	@Autowired
	MediationService mediationService;
	
	/**
	 * 회원 유형 선택에서 공인 중개소 주소
	 * @return
	 */
	
	@RequestMapping("mediationType")
	 public String mediationType() {
		return "thymeleaf/register/mediationagree";
	}
	/**
	 * 약관 동의 여부
	 * @return
	 */
	@RequestMapping(value = "regist1", method = RequestMethod.GET)
	   public String regist1() {
		return "thymeleaf/regist/mediationagree";
	}
	/**
	 * 약관 동의 여부
	 * @return
	 * @Param 
	 */
	@RequestMapping(value = "regist1", method = RequestMethod.POST)
	   public String regist1(
			   @RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
	   if(agree == false) {
		   return "thymeleaf/regist/agree";
	   }
	   return "thymeleaf/mediation/mediationForm";
	}
	@Autowired
	MediationListService mediationListService;
	@RequestMapping("mediationList")
	public String mediationList(String mediationWord,Model model) {
		mediationListService.execute(model,mediationWord);
		return "thymeleaf/register/mediationList";
	}
	/**
	 * 중개소 등록
	 * @return
	 */
	@RequestMapping(value = "mediationJoinAction", method = RequestMethod.GET)
	  public String mediationJoinAction() {
		return "redirect:/regist/agree";
	}
	/**
	 * 중개소 등록
	 * @param mediationCommand
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "mediationJoinAction", method = RequestMethod.POST)
	public String mediationJoinAction(@Validated MediationCommand mediationCommand, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "thymeleaf/mediation/mediationForm";
		}
		if (!mediationCommand.isMediationPwEqualsMediationPwCon()) {
			result.rejectValue("mediationPw", "mediationCommand.mediationPw", "비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/mediation/mediationForm";
		}
		mediationService.execute(mediationCommand, model);
		return "thymeleaf/mediation/welcomeMed";
	}
	/**
	 * 중개소 정보 상세 페이지
	 * @param mediationNum
	 * @param model
	 * @return
	 */
	@Autowired
	MediationDetailService mediationDetailService;
	@RequestMapping(value = "mediationDetail/{mediationNum}")
	public String mediationDeatail(@PathVariable(value = "mediationNum")String mediationNum, Model model) {
		mediationDetailService.execute(mediationNum,model);
		return "thymeleaf/register/mediationDetail";
	}	
	/**
	 * 중개소 정보 수정 페이지
	 * @param mediationNum
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "mediationModify", method = RequestMethod.GET)
	public String mediationModify(@RequestParam(value = "mediationNum")String mediationNum, Model model) {
		mediationDetailService.execute(mediationNum, model);
		return "thymeleaf/register/mediationUpdate";
	}
	/**
	 * 중개소 정보 수정
	 * @param memberCommand
	 * @param result
	 * @return
	 */
	@Autowired
	MediationModifyService mediationModifyService;
	@RequestMapping(value = "mediationModify", method = RequestMethod.POST)
	public String mediationModify(MediationCommand mediationCommand) {
		mediationModifyService.execute(mediationCommand);
		return "redirect:mediationDetail/"+mediationCommand.getMediationNum();
	}
	/**
	 * 중개소 정보 삭제
	 * @param mediationNum
	 * @return
	 */
	@Autowired
	MediationDeleteService mediationDeleteService;
	@RequestMapping("mediationDelete")
	public String mediationDelete(
			@RequestParam(value = "mediationNum")String mediationNum) {
		Integer i = mediationDeleteService.execute(mediationNum);
		if(i == 0) {
			return "redirect:mediationDetail/"+mediationNum;
		}
		return "redirect:mediationList";
	}
	/**
	 * 회원 선택 (배열_) 삭제
	 * @param medDels
	 * @return
	 */
	@Autowired
	MediationDelsService mediationDelsService;
	@RequestMapping("mediationDels")
	public String mediationDels(@RequestParam(value = "medDels")String medDels[]) {
		mediationDelsService.execute(medDels);
		return "redirect:mediationList";
	}
}