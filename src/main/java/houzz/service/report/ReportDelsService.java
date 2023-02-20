package houzz.service.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.ReportMapper;

@Service
public class ReportDelsService {
    @Autowired
    ReportMapper reportMapper;
	public void execute(String[] portDels) {
		List<String> css = new ArrayList<String>();
		for(String num1 : portDels) {
			css.add(num1);
		}
		HashMap<String, Object> condition =
				new HashMap<String, Object>();
		condition.put("reportNums", css);
		reportMapper.reportRemove(condition);
	}

}
