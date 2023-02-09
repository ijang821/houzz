package houzz.service.member;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.MemberCommand;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberMapper;

@Service
public class MemberModifyService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand) {
		MemberDTO memDTO = new MemberDTO();
		memDTO.setAccountAddress(memberCommand.getAccountAddress());
		memDTO.setMemberAddr(memberCommand.getMemberAddr());
		memDTO.setMemberEmail(memberCommand.getMemberEmail());
		memDTO.setMemberName(memberCommand.getMemberName());
		memDTO.setMemberPhone(memberCommand.getMemberPhone());
		memDTO.setMemberBirth(memberCommand.getMemberBirth());
		memDTO.setMemberId(memberCommand.getMemberId());
		memDTO.setMemberNum(memberCommand.getMemberNum());
		memberMapper.memberUpdate(memDTO);
	}
}
