package houzz.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.MemberCommand;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberMapper;

@Service
public class MemberDetailService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memberNum, Model model) {
		MemberDTO memDTO = memberMapper.selectOne(memberNum);
		model.addAttribute("memberCommand", memDTO);
		
	}
	public void execute(MemberCommand memberCommand, Model model) {
		MemberDTO memDTO = memberMapper.selectOne(memberCommand);
		model.addAttribute("memberCommand", memDTO);	
	}
}
