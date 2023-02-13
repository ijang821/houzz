package houzz.service.memberShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.MemberCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberInfoUpdateService {
	@Autowired
	MemberShipMapper memberShipMapper;

	public void execute(MemberCommand memberCommand, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO"); // 세션 가져옴
		String memId = authInfoDTO.getUserId(); // authInfod의 userId를 memId에 대입
		
		MemberDTO dto =  new MemberDTO();
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberPhone(memberCommand.getMemberPhone());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		memberShipMapper.updateMem(dto);
		model.addAttribute("memberCommand", dto);
	}
}
