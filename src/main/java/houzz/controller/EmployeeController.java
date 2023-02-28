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

import houzz.command.EmployeeCommand;
import houzz.service.employee.EmployeeDelService;
import houzz.service.employee.EmployeeDelsService;
import houzz.service.employee.EmployeeDetailService;
import houzz.service.employee.EmployeeListService;
import houzz.service.employee.EmployeeModifyService;
import houzz.service.employee.EmployeeNumService;
import houzz.service.employee.EmployeeWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	/**
	 * 직원 리스트 페이지
	 * @return
	 */
	@Autowired
	EmployeeListService employeeListService;
	@RequestMapping("empList")
	public String empList(@RequestParam(value = "empWord", required = false)String empWord, Model model,HttpSession session) {
		employeeListService.execute(empWord, model,session);
		return "thymeleaf/employee/empList";
	}
	
	/**
	 * 직원 등록 페이지
	 * @return
	 */
	@Autowired
	EmployeeNumService employeeNumService;
	@RequestMapping(value = "empRegist", method = RequestMethod.GET)
	public String empRegist(EmployeeCommand employeeCommand) {
		employeeNumService.execute(employeeCommand);
		return "thymeleaf/employee/empForm";
	}
	
	/**
	 * 직원 등록
	 * @return
	 */
	@Autowired
	EmployeeWriteService employeeWriteService; 
	@RequestMapping(value = "empRegist", method = RequestMethod.POST)
	public String empWrite(@Validated EmployeeCommand employeeCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/employee/empForm";
		}
		if(!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			result.rejectValue("empPwCon", "employeeCommand.empPwCon", "비밀번호 확인이 다릅니다");
		}
		employeeWriteService.execute(employeeCommand);
		return "redirect:/";
	}
	
	/**
	 * 직월 상세정보
	 * @param empNum
	 * @param model
	 * @return
	 */
	@Autowired
	EmployeeDetailService employeeDetailService;
	@RequestMapping("employeeDetail/{empNum}")
	public String empDetail(@PathVariable(value = "empNum")String empNum, Model model) {
		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/employeeDetail";
	}
	
	/**
	 * 직원 수정 페이지
	 * @param employeeCommand
	 * @return
	 */
	@RequestMapping(value = "empModify", method = RequestMethod.GET)
	public String empModify(@RequestParam(value = "empNum")String empNum, Model model) {
		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/empUpdate";
	}
	/**
	 * 직원 정보 수정
	 * @param employeeCommand
	 * @return
	 */
	@Autowired
	EmployeeModifyService employeeModifyService;
	@RequestMapping(value = "empModify", method = RequestMethod.POST)
	public String empModify(@Validated EmployeeCommand employeeCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/employee/empUpdate";
		}
		employeeModifyService.execute(employeeCommand);
		return "redirect:empDetail/"+employeeCommand.getEmpNum();
	}
	/**
	 * 직원 퇴사
	 * @param empNum
	 * @return
	 */
	@Autowired
	EmployeeDelService employeeDelService;
	@RequestMapping("empDelete")
	public String empDelete(@RequestParam("empNum")String empNum) {
		Integer i = employeeDelService.execute(empNum);
		if(i==0) {
			return "redirect:empDetail/"+empNum;
		}
		return "redirect:empList";
	}
	@Autowired
	EmployeeDelsService employeeDelsService;
	@RequestMapping("employeeDels")
	public String employeeDels(@RequestParam("empDels")String empDels[]) {
		employeeDelsService.execute(empDels);
		return "redirect:empList";
	} 
}
