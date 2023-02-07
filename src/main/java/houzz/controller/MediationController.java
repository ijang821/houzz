package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import houzz.command.MediationCommand;

import houzz.service.mediation.MediationService;

@Controller
public class MediationController {
	@Autowired
	MediationService mediationService;
	@RequestMapping("mediationJoinAction")
	public String mediationJoinAction(MediationCommand mediationCommand) {
		mediationService.execute(mediationCommand);
		return "thymeleaf/memberShip/mediationForm";
	}
}
