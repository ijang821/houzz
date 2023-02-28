package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import houzz.command.EmployeeCommand;
import houzz.domain.AuthInfoDTO;
import houzz.service.empShip.EmpInfoUpateService;
import houzz.service.empShip.EmpPasswordService;
import houzz.service.empShip.EmployeeInfoService;
import jakarta.servlet.http.HttpSession;

@Controller

public class MyPageEmpController {
	@ModelAttribute
	EmployeeCommand getEmployeeCommand() {
		return new EmployeeCommand();
	}

	@Autowired
	EmployeeInfoService employeeInfoService;

	/**
	 * 직원 내정보 보기
	 * 
	 * @return
	 */
	@RequestMapping(value = "empInfoUpdate",method = RequestMethod.GET)
	public String empInfo(Model model, HttpSession session) {
		employeeInfoService.execute(model, session);
		return "thymeleaf/employee/employeeDetail";
	}

	/**
	 * 직원 내정보 수정
	 * 
	 * @return
	 */
	@RequestMapping("employeeModify")
	public String employeeModify(Model model, HttpSession session) {
		employeeInfoService.execute(model, session);
		return "thymeleaf/employeeShip/empModify";
	}

	/**
	 * 직원 내정보 수정(업데이트)
	 * 
	 * @return
	 */
	@Autowired
	EmpInfoUpateService empInfoUpateService;

	@RequestMapping(value = "empInfoUpdate", method = RequestMethod.POST)
	public String empInfoUpdate(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors()) {
			return "thymeleaf/employeeShip/empModify";
		}
		empInfoUpateService.execute(employeeCommand, model, session);
		return "thymeleaf/employee/employeeDetail";
	}

	/**
	 * 직원 비밀번호 변경
	 * 
	 * @return
	 */
	@RequestMapping("empPass")
	public String empPass() {
		return "thymeleaf/employeeShip/empPass";
	}

	/**
	 * 직원 비밀번호 변경 전 기존 비밀번호 확인
	 * 
	 * @return
	 */
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping("empPwPro")
	public String empPwPro(@RequestParam(value = "empPw") String empPw, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		if (!passwordEncoder.matches(empPw, authInfoDTO.getUserPw())) {
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			return "thymeleaf/employeeShip/empPass";
		}
		return "thymeleaf/employeeShip/empPassCon";
	}
	/**
	 * 직원 새로운 비밀번호 변경
	 * 
	 * @return
	 */
	@Autowired
	EmpPasswordService empPasswordService;
	@RequestMapping("empPwModify")
	public String empPwModify(
			@RequestParam(value = "oldPw")String oldPw,
			@RequestParam(value = "empPw")String empPw,
			@RequestParam(value = "empPwCon")String empPwCon, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		if(!passwordEncoder.matches(oldPw, authInfoDTO.getUserPw())) {
			model.addAttribute("err_pw","비밀번호가 틀립니다.");
			return "thymeleaf/employeeShip/empPassCon";
		}
		if(!empPw.equals(empPwCon)) {
			model.addAttribute("err_pw_con","비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/employeeShip/empPassCon";
		}
		empPasswordService.execute(empPw,session);
		return "redirect:/";
	}

}
