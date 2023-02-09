package houzz.service.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.MemberMapper;

@Service
public class MemberDelsService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memDels[]) {
		List<String> cs = new ArrayList<String>();
		for(String num : memDels ) {
			cs.add(num);
		}
		//        키       값
		HashMap<String, Object> condition =
				new HashMap<String, Object>();
		condition.put("memberNums", cs);
		memberMapper.membersRemove(condition);
	}
}
