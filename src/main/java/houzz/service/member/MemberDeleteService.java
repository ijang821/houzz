package houzz.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.MemberMapper;

@Service
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	public Integer execute(String memberNum) {
		Integer i = memberMapper.memberDelete(memberNum);
		return i;
	}
}
