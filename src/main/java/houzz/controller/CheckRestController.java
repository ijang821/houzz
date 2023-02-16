package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import houzz.domain.AuthInfoDTO;
import houzz.service.EmailCheckService;
import houzz.service.IdCheckService;
import houzz.service.MemberEmailService;

@RestController
public class CheckRestController {
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	IdCheckService idCheckService;
	
	/**
	 * email 중복 확인
	 * @param memberEmail
	 * @param memberId
	 * @return
	 */
	@RequestMapping("member/memberEmailCk")
	public String memberEmailCk(@RequestParam(value = "memberEmail")String memberEmail,
								@RequestParam(value = "memberId")String memberId) {
		AuthInfoDTO authInfo = emailCheckService.execute(memberEmail);
		if(authInfo==null) {
			return "사용가능한 email입니다.";
		}else {
			if(authInfo.getUserId().equals(memberId)) {
				return "사용가능한 email입니다.";
			}else {
				return "사용중인 email입니다.";
			}
		}
	}
	/**
	 * id중복확인
	 * @param memberId
	 * @return
	 */
	@RequestMapping("member/memberIdCk")
	public String memberIdCheck(
			@RequestParam(value="memberId") String memberId) {
		String checkId = idCheckService.execute(memberId);
		String message=null;
		if (checkId == null) {
			message="사용가능한 id입니다.";
		}else {
			message="사용중인 id입니다.";
		}
		return message;
	}
	/**
	 * 직원 아이디 중복 확인
	 * @param empId
	 * @return
	 */
	@RequestMapping("employee/empIdCheck")
	public String empIdCheck(
			@RequestParam(value="empId") String empId) {
		String checkId = idCheckService.execute(empId);
		String message=null;
		if (checkId == null) {
			message="사용가능한 id입니다.";
		}else {
			message="사용중인 id입니다.";
		}
		return message;
	}
	@Autowired
	MemberEmailService memberEmailService;
	@RequestMapping(value = "/register/memberMail")
	public String memberMail(@RequestParam(value = "receiver")String memberEmail) {
		int i = memberEmailService.execute(memberEmail);
		if(i==1) {
			return "인증이 완료 되었습니다";
		}else {
			return "이미 인증이 되었습니다.";
		}
	}
}
