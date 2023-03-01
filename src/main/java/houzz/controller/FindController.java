package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.FindIdCommand;
import houzz.command.FindPwCommand;
import houzz.service.find.FindIdService;
import houzz.service.find.FindPwService;

@Controller
@RequestMapping("find")
public class FindController {
	@ModelAttribute
	FindIdCommand getFindIdCommand() {
		return new FindIdCommand();
	}
	

	@RequestMapping(value = "findId", method = RequestMethod.GET)
	public String findId(FindIdCommand findIdCommand) {
		return "thymeleaf/find/findId";
	}

	@Autowired
	FindIdService findIdService;

	@RequestMapping(value = "findId", method = RequestMethod.POST)
	public String findId(@Validated FindIdCommand findIdCommand, BindingResult result,Model model) {
		findIdService.execute(findIdCommand,result,model);

		return "thymeleaf/find/findIdOk";
	}
	@RequestMapping(value = "findPw", method = RequestMethod.GET)
	public String findPw(@ModelAttribute("findPwCommand") FindPwCommand findPwCommand) {
		return "thymeleaf/find/findPw";
	}
	@Autowired
	FindPwService findPwService;
	@RequestMapping(value = "findPw", method = RequestMethod.POST)
	public String findPw(@Validated FindPwCommand findPwCommand, BindingResult result) {
		if(result.hasErrors()) {
		return "thymeleaf/find/findPw";
		}
		findPwService.execute(findPwCommand,result);
		return "thymeleaf/find/findPwOk";
	}
}
