package houzz.service.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.domain.InquiryDTO;
import houzz.mapper.EmployeeMapper;
import houzz.mapper.InquiryMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class InquiryAnswerService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	InquiryMapper inquiryMapper;

	public void execute(String inquiryNum, HttpSession session, Model model) {

		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		EmployeeDTO empDTO = employeeMapper.selectOne(authInfoDTO.getUserId());
		model.addAttribute("empNum", empDTO.getEmpNum());

		InquiryDTO dto = inquiryMapper.selectOne(inquiryNum);

		model.addAttribute("inquiryNum", dto.getInquiryNum());

	}
}
