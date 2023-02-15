package houzz.service.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.InquiryMapper;

@Service
public class InquiryDeleteService {
	@Autowired
	InquiryMapper inquiryMapper;
	public Integer execute(String inquiryNum) {
		Integer i = inquiryMapper.inquiryDelete(inquiryNum);
       return i;
	}
}
