package houzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.MemberMapper;

@Service
public class IdCheckService {
	@Autowired
	MemberMapper memberMapper;
	public String execute(String memberId) {
		String memId = memberMapper.memberIdCk(memberId);
		return memId;
	}
}
