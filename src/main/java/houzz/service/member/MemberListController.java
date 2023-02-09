package houzz.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.MemberDTO;
import houzz.mapper.MemberMapper;

@Service
public class MemberListController {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memberWord, Model model) {
		List<MemberDTO> list = memberMapper.selectAll(memberWord);
		model.addAttribute("list", list);
		model.addAttribute("memberWord", memberWord);
	}
}
