package houzz.service.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.InquiryDTO;
import houzz.mapper.InquiryMapper;

@Service
public class InquiryListService {
    @Autowired
    InquiryMapper inquiryMapper;
	public void execute(String inquiryWord, Model model) {
		List<InquiryDTO> list = inquiryMapper.selectAll(inquiryWord);
		model.addAttribute("list",list);
	}
	 
}
