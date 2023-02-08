package houzz.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.MemberCommand;
import houzz.mapper.MemberMapper;

@Service
public class MemberNumService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand) {
		String num = memberMapper.memberNumGenerate();
		memberCommand.setMemberNum(num);
	}
}
