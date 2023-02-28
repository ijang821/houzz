package houzz.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.AuthInfoDTO;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemAccountAddressService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String accountAddress,HttpSession session, Model model) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO"); 
		String memberId = authInfoDTO.getUserId();
		
		MemberDTO memDTO = new MemberDTO();
		memDTO.setMemberId(memberId);
		memDTO.setAccountAddress(accountAddress);
		memberMapper.addAccountAddr(memDTO);
		
	}
}
