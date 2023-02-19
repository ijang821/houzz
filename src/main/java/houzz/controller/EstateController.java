package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import houzz.command.EstateCommand;
import houzz.command.FileInfo;
import houzz.command.ReportCommand;
import houzz.service.FileDownLoad;
import houzz.service.estate.EstateDeleteService;
import houzz.service.estate.EstateDetailService;
import houzz.service.estate.EstateListController;
import houzz.service.estate.EstateModifyService;
import houzz.service.estate.EstateNumService;
import houzz.service.estate.EstateRegistService;
import houzz.service.estate.FileDelService;
import houzz.service.report.ReportAutoNumService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("estate")
public class EstateController {
	/**
	 * 매물 리스트 페이지
	 * @return
	 */
	@Autowired
	EstateListController estateListController;
	@RequestMapping("estateList")
	public String estateList(@RequestParam(value = "estateWord", required = false)String estateWord, Model model ) {
		estateListController.execute(estateWord, model);
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
	/**
	 * 매물 등록, PDF파일 생성
	 * @param estateCommand
	 * @param session
	 * @param result
	 * @return
	 */
	@Autowired
	EstateRegistService estateRegistService;
	@RequestMapping(value = "estateRegist", method = RequestMethod.POST)
	public String estateRegist(@Validated EstateCommand estateCommand, 
							   @RequestParam(value="fileName", required = false) String fileName, 
							   HttpSession session, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/estate/estateForm";
		}
		if(estateCommand.getEstatePic()[0].getOriginalFilename().isEmpty()) {
			result.rejectValue("estatePic", "estateCommand.estatePic", "이미지를 선택하여주세요.");
			return "thymeleaf/estate/estateForm";
		}
		estateRegistService.execute(estateCommand, session);
		estateRegistService.createPdf(estateCommand, fileName);
		return "thymeleaf/estate/registDone";
	}
	
	/**
	 * pdf다운로드
	 * @param path
	 * @param fileName
	 * @param ofileName
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired
	FileDownLoad fileDownLoad;
	@RequestMapping(value = "downPDF", method = RequestMethod.GET)
	public String downPDF(String path, String fileName, String ofileName ,HttpServletRequest request, HttpServletResponse response) {
		fileDownLoad.fileDownLoad(path, fileName, ofileName, request, response);
		return "redirect:estateList";
	}
	
	/**
	 * 매물 상세 정보 보기
	 * @param estateNum
	 * @param model
	 * @return
	 */
	@Autowired
	EstateDetailService estateDetailService;
	@RequestMapping(value = "estateDetail/{estateNum}")
	public String estateDetail(@PathVariable(value = "estateNum")String estateNum, Model model) {
		estateDetailService.execute(estateNum, model);
		return "thymeleaf/estate/estateDetail";
	}
	/**
	 * 수정 페이지
	 * @param estateNum
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "estateModify", method = RequestMethod.GET)
	public String estateModify(@RequestParam(value = "estateNum")String estateNum, Model model) {
		estateDetailService.execute(estateNum, model);
		return "thymeleaf/estate/estateUpdate";
	}
	/**
	 * 매물 수정
	 * @param estateCommand
	 * @param result
	 * @return
	 */
	@Autowired
	EstateModifyService estateModifyService;
	@RequestMapping(value = "estateModify", method = RequestMethod.POST)
	public String estateModify(@Validated EstateCommand estateCommand, HttpSession session, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/estate/estateUpdate";
		}
		estateModifyService.execute(estateCommand, session);
		return "redirect:estateDetail/"+estateCommand.getEstateNum();
	}
	/**
	 * 매물 삭제
	 * @param estateNum
	 * @param request
	 * @return
	 */
	@Autowired
	EstateDeleteService estateDeleteService;
	@RequestMapping("estateDelete")
	public String estateDelete(@RequestParam(value = "estateNum")String estateNum, HttpServletRequest request) {
		estateDeleteService.execute(estateNum, request);
		return "redirect:estateList";
	}
	/**
	 * 파일 삭제 서비스
	 * @param fileInfo
	 * @param session
	 * @param model
	 * @return
	 */
	@Autowired
	FileDelService fileDelService;
	@RequestMapping(value = "fileDel")
	public String fileDel(FileInfo fileInfo, HttpSession session, Model model) {
		fileDelService.execute(fileInfo, session, model);
		return "thymeleaf/estate/delPage";
	}
	
}
