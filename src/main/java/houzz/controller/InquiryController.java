package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import houzz.command.InquiryCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.domain.MemberDTO;
import houzz.mapper.EmployeeMapper;
import houzz.service.inquiry.InquiryAnswerService;
import houzz.service.inquiry.InquiryAutoNum;
import houzz.service.inquiry.InquiryDeleteService;
import houzz.service.inquiry.InquiryDelsService;
import houzz.service.inquiry.InquiryDetailService;
import houzz.service.inquiry.InquiryInsertService;
import houzz.service.inquiry.InquiryListService;
import houzz.service.inquiry.InquiryModifyService;
import jakarta.servlet.http.HttpSession;

@Controller
public class InquiryController {
	/**
	 * 문의글 목록
	 * 
	 * @return
	 */
	@Autowired
	InquiryListService inquiryListService;
	@RequestMapping("inquiryList")
	public String inquiryList(String inquiryWord,Model model) {
		inquiryListService.execute(inquiryWord,model);
		return "thymeleaf/inquiry/inquiryList";
	}
	/**
	 * 문의 글 등록 초기 화면/ 번호 자동 부여
	 * 
	 * @return
	 */
	@Autowired
	InquiryAutoNum inquiryAutoNum;
	@RequestMapping("inquiryWrite")
	public String inquiryWrite(InquiryCommand inquiryCommand,HttpSession session) {
		inquiryAutoNum.execute(inquiryCommand,session);
		return "thymeleaf/inquiry/inquiryForm";
	}
	/**
	 * 회원이 문의글 등록 
	 * 
	 * @return
	 */
	@Autowired
	InquiryInsertService inquiryInsertService;
	@RequestMapping(value = "inquiryRegist", method = RequestMethod.POST)
	public String inquiryRegist(InquiryCommand inquiryCommand, Model model, HttpSession session) {
		inquiryInsertService.execute(inquiryCommand,model,session);
		return "redirect:/";
	}
	
	/**
	 * 회원이 문의글 상세보기
	 * 
	 * @return
	 */
	@Autowired
	InquiryDetailService inquiryDetailService;
	@RequestMapping(value = "inquiryDetail/{inquiryNum}")
	public String inquiryDetail(
			@PathVariable(value = "inquiryNum") String inquiryNum, Model model) {
		inquiryDetailService.execute(inquiryNum,model);
		return "thymeleaf/inquiry/inquiryDetail";
	}
	/**
	 * 회원이 문의글 수정하기(get 방식)
	 * 
	 * @return
	 */
	@RequestMapping(value = "inquiryModify", method = RequestMethod.GET)
	public String inquiryModify(
			@RequestParam(value = "inquiryNum") String inquiryNum,Model model) {
		inquiryDetailService.execute(inquiryNum,model);
		return "thymeleaf/inquiry/inquiryUpdate";
	}
	/**
	 * 회원이 문의글 수정하기(post방식)
	 * 
	 * @return
	 */
	@Autowired
	InquiryModifyService inquiryModifyService;
	@RequestMapping(value = "inquiryModify", method = RequestMethod.POST)
	public String inquiryModify(InquiryCommand inquiryCommand) {
		inquiryModifyService.execute(inquiryCommand);
		return "redirect:inquiryDetail/"+inquiryCommand.getInquiryNum();
    }
	/**
	 * 회원이 문의글 삭제하기
	 * 
	 * @return
	 */
	@Autowired
	InquiryDeleteService inquiryDeleteService;
	@RequestMapping("inquiryDelete")
	public String inquiryDelete(
			@RequestParam(value = "inquiryNum") String inquiryNum) {
		Integer i = inquiryDeleteService.execute(inquiryNum);
		if(i ==0) {
			return "redirect:inquiryDetail/"+inquiryNum;
		}
		return "redirect:inquiryList";
	}
	/**
	 * 회원이 선택 (배열_) 삭제
	 * 
	 * @return
	 */
	@Autowired
	InquiryDelsService inquiryDelsService;
	@RequestMapping("inquiryDels")
	public String inquiryDels(@RequestParam(value = "inqDels")String inqDels[]) {
		inquiryDelsService.execute(inqDels);
		return "redirect:inquiryList";
	}
	/**
	 * 직원이 문의 답변
	 * 
	 * @return
	 */
	
	@Autowired
	InquiryAnswerService inquiryAnswerService;
	@RequestMapping(value = "inquiryAnswer")
	public String inquiryAnswer(InquiryCommand inquiryCommand, HttpSession session) {
		inquiryAnswerService.execute(inquiryCommand,session);
		return"thymeleaf/inquiry/inquieyAnswer";
	}

}