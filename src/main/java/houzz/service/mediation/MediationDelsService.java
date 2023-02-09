package houzz.service.mediation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.MediationMapper;

@Service
public class MediationDelsService {
	@Autowired
	MediationMapper mediationMapper;

	public void execute(String[] medDels) {
		List<String> cs = new ArrayList<String>(); 
		for(String num : medDels) {
			cs.add(num);
		}
		HashMap<String, Object> condition =
				new HashMap<String, Object>();
		condition.put("mediationNums", cs);
		mediationMapper.mediationRemove(condition);
	}
}
