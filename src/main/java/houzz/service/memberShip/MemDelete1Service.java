package houzz.service.memberShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.domain.AuthInfoDTO;
import houzz.mapper.MemberShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemDelete1Service {
    @Autowired
    MemberShipMapper memberShipMapper;
	public void execute(HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		String memId = authInfoDTO.getUserId();
		memberShipMapper.memDel(memId);
		
	}
      
}
