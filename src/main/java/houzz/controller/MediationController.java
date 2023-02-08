package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import houzz.command.MediationCommand;
import houzz.service.mediation.MediationService;

@Controller
@RequestMapping("register")
public class MediationController {
	@ModelAttribute
    MediationCommand getMediationCommand() {
		return new MediationCommand();
	}

	@Autowired
	MediationService mediationService;
	
	@RequestMapping(value = "regist1", method = RequestMethod.GET)
	   public String regist1() {
		return "thymeleaf/regist/agree";
	}
	@RequestMapping(value = "regist1", method = RequestMethod.POST)
	   public String regist1(
			   @RequestParam(value = "agree", defaultValue = "false") Boolean agree) {
	   if(agree == false) {
		   return "thymeleaf/regist/agree";
	   }
	   return "thymeleaf/memberShip/mediationForm";
	}
	@RequestMapping(value = "mediationJoinAction", method = RequestMethod.GET)
	  public String mediationJoinAction() {
		return "redirect:/regist/agree";
	}
	
	@RequestMapping(value = "mediationJoinAction", method = RequestMethod.POST)
	public String mediationJoinAction(@Validated MediationCommand mediationCommand, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "thymeleaf/memberShip/mediationForm";
		}
		if (!mediationCommand.isMediationPwEqualsMediationPwCon()) {
			result.rejectValue("mediationPw", "mediationCommand.mediationPw", "비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/memberShip/mediationForm";
		}
		mediationService.execute(mediationCommand, model);
		return "thymeleaf/memberShip/welcomeMed";
	}
}