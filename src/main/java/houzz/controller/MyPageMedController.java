package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import houzz.command.MediationCommand;
import houzz.service.mediationShip.MediationInfoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageMedController {
	@ModelAttribute
	MediationCommand getMediationCommand() {
		return new MediationCommand();
	}
	
	
	@Autowired
	MediationInfoService mediationInfoService;
	
     @RequestMapping("mediationDetail")
         public String mediationDetail(Model model, HttpSession session) {
    	 mediationInfoService.execute(model,session);
    	 return "thymeleaf/mediationShip/medDetail";
     }
}
