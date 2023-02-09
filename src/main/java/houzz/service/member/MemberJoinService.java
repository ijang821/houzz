package houzz.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.MemberCommand;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberJoinMapper;

@Service
public class MemberJoinService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberJoinMapper memberJoinMapper;
	Integer i;
	public Integer execute(MemberCommand memberCommand, Model model) {
		MemberDTO memDTO = new MemberDTO();
		memDTO.setAccountAddress(memberCommand.getAccountAddress());
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
		i = memberJoinMapper.memberJoinInsert(memDTO);
		model.addAttribute("memberName",memDTO.getMemberName());
		return i;	

	}
}