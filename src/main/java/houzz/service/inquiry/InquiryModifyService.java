package houzz.service.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.InquiryCommand;
import houzz.domain.InquiryDTO;
import houzz.mapper.InquiryMapper;

@Service
public class InquiryModifyService {
    @Autowired
    InquiryMapper inquiryMapper;
    Integer i;
	public Integer execute(InquiryCommand inquiryCommand) {
		InquiryDTO dto = new InquiryDTO();
		dto.setAnswerContent(inquiryCommand.getAnswerContent());
		dto.setAnswerDate(inquiryCommand.getAnswerDate());
		dto.setEmpNum(inquiryCommand.getEmpNum());
		dto.setInquiryContent(inquiryCommand.getInquiryContent());
		dto.setInquiryDate(inquiryCommand.getInquiryDate());
		dto.setInquiryNum(inquiryCommand.getInquiryNum());
		dto.setInquiryTitle(inquiryCommand.getInquiryTitle());
		dto.setMemberNum(inquiryCommand.getMemberNum());
		Integer i = inquiryMapper.inquiryUpdate(dto);
		System.out.println(i +"개가 업데이트 되었습니다.");
		return i;
	}
}
