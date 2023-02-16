package houzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.domain.AuthInfoDTO;
import houzz.mapper.MemberMapper;

@Service
public class EmailCheckService {
	@Autowired
	MemberMapper memberMapper;
	public AuthInfoDTO execute(String userEmail) {
		AuthInfoDTO authInfo =  memberMapper.memberEmail(userEmail);
		return authInfo;
	}
}
