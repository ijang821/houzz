package houzz.service.memberShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import houzz.domain.AuthInfoDTO;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberPasswordService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberShipMapper memberShipMapper;
	public void execute(String memberPw, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		String newPw = passwordEncoder.encode(memberPw);
		MemberDTO dto = new MemberDTO();
		dto.setMemberId(authInfoDTO.getUserId());
		dto.setMemberPw(newPw);
		memberShipMapper.updatePw(dto);
		authInfoDTO.setUserPw(newPw);
	}

}
