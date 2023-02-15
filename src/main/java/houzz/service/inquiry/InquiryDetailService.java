package houzz.service.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.InquiryCommand;
import houzz.domain.InquiryDTO;
import houzz.mapper.InquiryMapper;

@Service
public class InquiryDetailService {
    @Autowired
    InquiryMapper inquiryMapper;
	public void execute(String inquiryNum, Model model) {
		InquiryDTO inqDTO = inquiryMapper.selectOne(inquiryNum);
		model.addAttribute("inquiryCommand",inqDTO);
	}	
}