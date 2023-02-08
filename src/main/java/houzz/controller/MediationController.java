package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.MediationCommand;
import houzz.service.mediation.MediationService;


@Controller
@RequestMapping("register")
public class MediationController {
	@Autowired
	MediationService mediationService;
	/**
	 * 공인중개소 등록
	 * 
	 */
	@RequestMapping(value = "mediationJoinAction", method = RequestMethod.POST)
	public String mediationJoinAction(MediationCommand mediationCommand, Model model) {
		mediationService.execute(mediationCommand,model);
		return "thymeleaf/memberShip/welcome2";
	}

}