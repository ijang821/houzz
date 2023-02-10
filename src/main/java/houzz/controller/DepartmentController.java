package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.DepartmentCommand;
import houzz.service.department.DepartmentListService;
import houzz.service.department.DepartmentRegistService;

@Controller
@RequestMapping("department")
public class DepartmentController {
	/**
	 * 부서 리스트 페이지
	 * @return
	 */
	@Autowired
	DepartmentListService departmentListService;
	@RequestMapping("departmentList")
	public String departmentList(Model model) {
		departmentListService.execute(model);
		return "thymeleaf/department/departmentList";
	}
	
	/**
	 * 부서 등록 페이지
	 * @return
	 */
	@RequestMapping(value = "departmentRegist", method = RequestMethod.GET)
	public String departmentRegist(DepartmentCommand departmentCommand) {
		return "thymeleaf/department/departmentRegist";
	}
	/**
	 * 부서 등록
	 * @param departmentCommand
	 * @param result
	 * @return
	 */
	@Autowired
	DepartmentRegistService departmentRegistService;
	@RequestMapping(value = "departmentRegist", method = RequestMethod.POST)
	public String departmentRegist(@Validated DepartmentCommand departmentCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/department/departmentRegist";
		}
		Integer i = departmentRegistService.execute(departmentCommand);
		if(i!=null) {
			return "redirect:departmentList";
		}
		else return "thymeleaf/department/departmentRegist";
	}
}
