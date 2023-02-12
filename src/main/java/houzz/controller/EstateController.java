package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.EstateCommand;
import houzz.service.estate.EstateNumService;

@Controller
@RequestMapping("estate")
public class EstateController {
	/**
	 * 매물 리스트 페이지
	 * @return
	 */
	@RequestMapping("estateList")
	public String estateList() {
		return "thymeleaf/estate/estateList";
	}
	/**
	 * 매물 등록 페이지
	 * @param estateCommand
	 * @return
	 */
	@Autowired
	EstateNumService estateNumService;
	@RequestMapping(value = "estateRegist", method = RequestMethod.GET)
	public String estateRegist(EstateCommand estateCommand) {
		estateNumService.execute(estateCommand);
		return "thymeleaf/estate/estateForm";
	}
}
