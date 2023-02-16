package houzz.service.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.InquiryCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.InquiryDTO;
import houzz.domain.MemberDTO;
import houzz.mapper.InquiryMapper;
import houzz.mapper.MemberMapper;
import houzz.mapper.MemberShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class InquiryAutoNum {
    @Autowired
    InquiryMapper inquiryMapper;
    @Autowired
    MemberShipMapper memberShipMapper;
	public void execute(InquiryCommand inquiryCommand,HttpSession session) {  
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		MemberDTO memDTO = memberShipMapper.selectOne(authInfoDTO.getUserId());
		
	    System.out.println(authInfoDTO.getUserId());
		String num = inquiryMapper.inquiryNumGenerate();
		
		inquiryCommand.setInquiryNum(num);
		inquiryCommand.setMemberNum(memDTO.getMemberNum());
	}  
}
