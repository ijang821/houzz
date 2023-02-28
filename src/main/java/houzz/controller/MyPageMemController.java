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

import houzz.command.MemberCommand;
import houzz.domain.AuthInfoDTO;
import houzz.mapper.MemberShipMapper;
import houzz.service.member.MemAccountAddressService;
import houzz.service.memberShip.MemDelete1Service;
import houzz.service.memberShip.MemberInfoService;
import houzz.service.memberShip.MemberInfoUpdateService;
import houzz.service.memberShip.MemberPasswordService;
import jakarta.servlet.http.HttpSession;

@Controller

public class MyPageMemController {
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@ModelAttribute
	public MemberCommand getMemberCommand() {
		return new MemberCommand();
	}

	/**
	 * 내정보 보기 (회원 아이디 세션 가져옴)
	 * 
	 * @return
	 */
	@RequestMapping("memDetail")
	public String memInfo(Model model, HttpSession session) {
		memberInfoService.execute(model, session);
		return "thymeleaf/memberShip/memDetail";
	}

	/**
	 * 내정보 수정 (상세페이지꺼 그래도 가지고 오기)
	 * 
	 * @return
	 */
	@Autowired
	MemberInfoUpdateService memberInfoUpdateService;

	@RequestMapping("memModify")
	public String memModify(Model model, HttpSession session) {
		memberInfoService.execute(model, session);
		return "thymeleaf/memberShip/memModify";
	}

	/**
	 * 내정보 수정
	 * 
	 * @return
	 */
	@RequestMapping(value = "memInfoUpdate", method = RequestMethod.POST)
	public String memInfoUpdate(@Validated MemberCommand memberCommand, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors()) {
			return "thymeleaf/memberShip/memModify";
		}
		memberInfoUpdateService.execute(memberCommand, model, session);
		return "redirect:memDetail";
	}

	/**
	 * 내정보 수정
	 * 
	 * @return
	 */

	/**
	 * 회원 비밀번호 수정(회원 비밀번호 세션 가져옴)
	 * 
	 * @Param model, session
	 */
	@RequestMapping("memPass")
	public String memPass() {
		return "thymeleaf/memberShip/memPass";
	}

	@RequestMapping(value = "memPwPro", method = RequestMethod.POST)
	public String memPwPro(@RequestParam(value = "memPw") String memPw, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		if (!passwordEncoder.matches(memPw, authInfoDTO.getUserPw())) {
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			return "thymeleaf/memberShip/memPass";
		}
		return "thymeleaf/memberShip/memPassCon";
	}

	/**
	 * 회원 새로운 비밀번호 변경(회원 비밀번호 세션 가져옴)
	 * 
	 * @Param session
	 */
	@Autowired
	MemberPasswordService memberPasswordService;

	@RequestMapping(value = "memPwModify", method = RequestMethod.POST)
	public String memPwModify(@RequestParam(value = "oldPw") String oldPw,
			@RequestParam(value = "memberPw") String memberPw, @RequestParam(value = "memberPwCon") String memberPwCon,
			Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		if (!passwordEncoder.matches(oldPw, authInfoDTO.getUserPw())) {
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			return "thymeleaf/memberShip/memPassCon";
		}
		if (!memberPw.equals(memberPwCon)) {
			model.addAttribute("err_pw_con", "비밀번호와 비밀번호 확인이 다릅니다.");
			return "thymeleaf/memberShip/memPassCon";
		}
		memberPasswordService.execute(memberPw, session);
		return "redirect:/";
	}

	@RequestMapping("memDelete1")
	public String memDelete1() {
		return "thymeleaf/memberShip/memberDrop";
	}

	@Autowired
	MemDelete1Service memDelete1Service;

	@RequestMapping(value = "MemDrop", method = RequestMethod.POST)
	public String MemDrop(HttpSession session) {
		memDelete1Service.execute(session);
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	@RequestMapping("accountAdd")
	public String accountAdd() {
		return "thymeleaf/memberShip/accountAddrAdd";
	}
	/**
	 * 지갑주소 추가
	 * @param session
	 * @param memberCommand
	 * @param model
	 * @return
	 */
	@Autowired
	MemAccountAddressService memAccountAddressService;
	@Autowired
	MemberShipMapper memberShipMapper;
	@RequestMapping("accountAddress")
	public void accAddrSuccess(@RequestParam("accountAddress")String accountAddress, HttpSession session, Model model) {
		memAccountAddressService.execute(accountAddress, session, model);
	}
}
