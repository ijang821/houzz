package houzz.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import houzz.command.MemberCommand;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberMapper;

@Service
public class MemberRegistService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	Integer i;
	public Integer execute(MemberCommand memberCommand) {
		MemberDTO memDTO = new MemberDTO();
		memDTO.setMemberAddr(memberCommand.getMemberAddr());
		memDTO.setMemberBirth(memberCommand.getMemberBirth());
		memDTO.setMemberEmail(memberCommand.getMemberEmail());
		memDTO.setMemberGender(memberCommand.getMemberGender());
		memDTO.setMemberId(memberCommand.getMemberId());
		memDTO.setMemberJoinDate(memberCommand.getMemberJoinDate());
		memDTO.setMemberName(memberCommand.getMemberName());
		memDTO.setMemberNum(memberCommand.getMemberNum());
		memDTO.setMemberPhone(memberCommand.getMemberPhone());
		memDTO.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));
		memDTO.setAccountAddress(memberCommand.getAccountAddress());
		i = memberMapper.memberInsert(memDTO);
		return i;
	}
}
