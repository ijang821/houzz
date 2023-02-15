package houzz.service.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.InquiryCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.InquiryDTO;
import houzz.domain.MemberDTO;
import houzz.mapper.InquiryMapper;
import houzz.mapper.MemberShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class InquiryInsertService {
    @Autowired
    InquiryMapper inquiryMapper;
    @Autowired
    MemberShipMapper memberShipMapper;
    Integer i;
	public Integer execute(InquiryCommand inquiryCommand, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		String userId = authInfoDTO.getUserId();
		InquiryDTO dto = new InquiryDTO();
		dto.setMemberNum(userId);
		dto.setInquiryContent(inquiryCommand.getInquiryContent());
		dto.setInquiryTitle(inquiryCommand.getInquiryTitle());
		dto.setInquiryDate(inquiryCommand.getInquiryDate());
		dto.setEmpNum(userId);
		dto.setInquiryNum(inquiryCommand.getInquiryNum());
		i = inquiryMapper.inquiryInsert(dto);
		System.out.println(i + "삽입 성공");
		model.addAttribute("inquiryCommand",dto);
	    
		return i;
		
	}
}
