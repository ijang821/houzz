package houzz.service.mediationShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import houzz.domain.AuthInfoDTO;
import houzz.domain.MediationDTO;
import houzz.mapper.MediationShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MediationPasswordService {
	@Autowired
	MediationShipMapper mediationShipMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(String mediationPw, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		String newPw = passwordEncoder.encode(mediationPw);
		MediationDTO dto = new MediationDTO();
		dto.setMediationId(authInfoDTO.getUserId());
		dto.setMediationPw(newPw);
		mediationShipMapper.updatePwMed(dto);
		authInfoDTO.setUserPw(newPw);
	}   
}
