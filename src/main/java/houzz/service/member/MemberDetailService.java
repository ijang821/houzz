package houzz.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
}
