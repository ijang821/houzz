package houzz.service.memberShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.AuthInfoDTO;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberInfoService {
    @Autowired
    MemberShipMapper memberShipMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO"); // 세션 가져옴
		    String memId = authInfoDTO.getUserId();   // authInfod의 userId를 memId에 대입
		    System.out.println(memId); // memId 확인
		    MemberDTO dto = memberShipMapper.selectMem(memId);  
		    model.addAttribute("memberCommand",dto);
	}
	
}
