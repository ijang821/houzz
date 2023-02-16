package houzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.MemberMapper;

@Service
public class MemberEmailService {
	@Autowired
	MemberMapper memberMapper;
	public int execute(String memberEmail) {
		int i = memberMapper.memJoinCk(memberEmail);
		return i;
	}
}
