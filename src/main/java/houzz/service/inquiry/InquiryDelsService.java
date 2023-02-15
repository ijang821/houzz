package houzz.service.inquiry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.InquiryMapper;

@Service
public class InquiryDelsService {
    @Autowired
    InquiryMapper inquiryMapper;
	public void execute(String[] inqDels) {
		List<String> cs = new ArrayList<String>();
		for(String num : inqDels) {
			cs.add(num);
		}
		HashMap<String, Object> condition =
				new HashMap<String, Object>();
		condition.put("inquiryNums", cs);
		inquiryMapper.mediationRemove(condition);
	}
     
}
