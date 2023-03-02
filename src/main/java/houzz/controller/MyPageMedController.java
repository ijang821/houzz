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

import houzz.command.MediationCommand;
import houzz.domain.AuthInfoDTO;
import houzz.service.mediationShip.MedDelete1Service;
import houzz.service.mediationShip.MedInfoUpdateService;
import houzz.service.mediationShip.MediationInfoService;
import houzz.service.mediationShip.MediationPasswordService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageMedController {
	@Autowired
	MediationInfoService mediationInfoService;
	@ModelAttribute
	MediationCommand getMediationCommand() {
		return new MediationCommand();
	}
     
	/**
	 * 공인 중개소 내정보 보기
	 * 
	 * @return
	 */
	@RequestMapping("mediationDetail")
	public String mediationDetail(Model model, HttpSession session) {
		mediationInfoService.execute(model, session);
		return "thymeleaf/mediationShip/medDetail";
	}
	
	/**
	 * 공인 중개소 내정보 수정
	 * 
	 * @return
	 */
	
	@RequestMapping("mediationModify")
	public String mediationModify(Model model, HttpSession session) {
		mediationInfoService.execute(model,session);
		return "thymeleaf/mediationShip/medModify";
	}
	
	/**
	 * 공인 중개소 내정보 수정 (업데이트)
	 * 
	 * @return
	 */
	@Autowired
	MedInfoUpdateService medInfoUpdateService;
	@RequestMapping(value = "medInfoUpdate", method = RequestMethod.POST)
	public String medInfoUpdate(@Validated MediationCommand mediationCommand, BindingResult result, 
			                      Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/mediationShip/medModify";
		}
		medInfoUpdateService.execute(mediationCommand, model, session);
		return "thymeleaf/mediationShip/medDetail";
		}
	/**
	 * 공인 중개소 비밀번호 변경
	 * 
	 * @return
	 */
    
	@RequestMapping("mediationPass")
	public String mediationPass() {
		return "thymeleaf/mediationShip/medPass";
	}
	/**
	 * 공인 중개소 비밀번호 변경전 기존 비밀번호 확인
	 * 
	 * @return
	 */
	@Autowired
	PasswordEncoder passwordEncoder;
	@RequestMapping("medPwPro")
	public String medPwPro(@RequestParam(value = "medPw") String medPw, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		if (!passwordEncoder.matches(medPw, authInfoDTO.getUserPw())) {
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			return "thymeleaf/mediationShip/medPass";
		}
		return "thymeleaf/mediationShip/medPassCon";
	}
	/**
	 * 공인 중개소 새로운 비밀번호 변경
	 * 
	 * @return
	 */
	@Autowired
	MediationPasswordService mediationPasswordService;
	@RequestMapping("medPwModify")
	public String medPwModify(
			@RequestParam(value = "oldPw")String oldPw,
			@RequestParam(value = "mediationPw")String mediationPw,
			@RequestParam(value = "mediationPwCon")String mediationPwCon, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		if(!passwordEncoder.matches(oldPw, authInfoDTO.getUserPw())) {
			model.addAttribute("err_pw","비밀번호가 틀립니다.");
			return "thymeleaf/mediationShip/medPassCon";
		}
		if(!mediationPw.equals(mediationPwCon)) {
			model.addAttribute("err_pw_con","비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/mediationShip/medPassCon";
		}
		mediationPasswordService.execute(mediationPw,session);
		return "redirect:/";
	}
	/**
	 * 공인 중개소 탈퇴 재확인 페이지
	 * 
	 * @return
	 */
	@RequestMapping("medDelete")
	public String medDelete() {
		return "thymeleaf/mediationShip/mediatonDrop";
	}
	/**
	 * 공인 중개소 탈퇴 (세션 날리기)
	 * 
	 * @return
	 */
	@Autowired
	MedDelete1Service medDelete1Service;
	@RequestMapping("mediationDrop")
	public String mediationDrop(HttpSession session) {
		medDelete1Service.execute(session);
		session.invalidate();
		return "thymeleaf/mediationShip/medSecession";
	}
	
	
}
