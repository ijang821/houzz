package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import houzz.command.ReportCommand;
import houzz.service.report.ReportAutoNumService;
import houzz.service.report.ReportDetailService;
import houzz.service.report.ReportEmpUpdateService;
import houzz.service.report.ReportListService;
import houzz.service.report.ReportRegistService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("report")
public class ReportController {	
	/**
	 * 신고 목록
	 * @param 
	 * @param 
	 * @return
	 */
	@Autowired
	ReportListService reportListService;
	@RequestMapping("/reportList")
	public String reportList(Model model) {
		reportListService.execute(model);
		return "thymeleaf/report/reportList";
	}
	/**
	 * 매물 신고
	 * @param 
	 * @param 
	 * @return
	 */
	@Autowired
	ReportAutoNumService reportAutoNumService;
	@RequestMapping(value = "/reportWrite")
	public String reportWrite(ReportCommand reportCommand,HttpSession session) {
		reportAutoNumService.execute(reportCommand,session);
		return "thymeleaf/report/reportForm";
	}
	/**
	 * 매물 신고 상세페이지( get방식 직접접속 차단)
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "reportRegist", method = RequestMethod.GET)
	public String reportRegist() {
		return "thymeleaf/report/reportMem";
	}
	/**
	 * 매물 신고 상세페이지(post)
	 * @param 
	 * @param 
	 * @return
	 */
	@Autowired
	ReportRegistService reportRegistService;
	@RequestMapping(value = "reportRegist", method = RequestMethod.POST)
	public String reportInfo(ReportCommand reportCommand) {
		reportRegistService.execute(reportCommand);
		return "thymeleaf/report/reportMem";
	}
	@Autowired
	ReportDetailService reportDetailService;
	@RequestMapping(value = "reportDetail/{reportNum}")
	public String reportDetail(
			@PathVariable(value = "reportNum") String reportNum, Model model, HttpSession session) {
		reportDetailService.execute(reportNum, model,session);
		return "thymeleaf/report/reportDetail";
	}
	@Autowired
	ReportEmpUpdateService reportEmpUpdateService;
	@RequestMapping("reportAnswer")
	public String reportAnswer(String reportNum, Model model,HttpSession session) {
		reportDetailService.execute(reportNum,model,session);
		return "thymeleaf/report/reportEmpAnswer";
	}
	@RequestMapping(value = "reportAnswerUpdate",method = RequestMethod.POST)
	public String reportAnswerUpdate(ReportCommand reportCommand,HttpSession session) {
		reportEmpUpdateService.execute(reportCommand,session);
		return "redirect:reportDetail/"+reportCommand.getReportNum();
	}
}
