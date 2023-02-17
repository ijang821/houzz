package houzz.service.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.InquiryCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.domain.InquiryDTO;
import houzz.mapper.EmployeeMapper;
import houzz.mapper.InquiryMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class InquiryAnswerUpateService {
    @Autowired
    InquiryMapper inquiryMapper;
    @Autowired
	EmployeeMapper employeeMapper;
	public void execute(InquiryCommand inquiryCommand, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		EmployeeDTO empDTO = employeeMapper.selectOne(authInfoDTO.getUserId());
		System.out.println(empDTO.getEmpNum());
		inquiryCommand.setEmpNum(empDTO.getEmpNum());
		InquiryDTO dto = new InquiryDTO();
		dto.setAnswerContent(inquiryCommand.getAnswerContent());
		dto.setAnswerDate(inquiryCommand.getAnswerDate());
		dto.setEmpNum(inquiryCommand.getEmpNum());
		dto.setInquiryNum(inquiryCommand.getInquiryNum());
	
		inquiryMapper.inquiryAnswerUpdate(dto);
	}

}
