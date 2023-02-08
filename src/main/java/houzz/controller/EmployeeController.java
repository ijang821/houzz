package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.EmployeeCommand;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	/**
	 * 직원 리스트 페이지
	 * @return
	 */
	@RequestMapping("empList")
	public String empList() {
		return "thymeleaf/employee/empList";
	}
	
	/**
	 * 직원 등록 페이지
	 * @return
	 */
	@RequestMapping(value = "empRegist", method = RequestMethod.GET)
	public String empRegist() {
		return "thymeleaf/employee/empForm";
	}
	
	/**
	 * 직원 등록
	 * @return
	 */
	@RequestMapping(value = "empRegist", method = RequestMethod.POST)
	public String empWrite() {
		return "redirect:empList";
	}
}
