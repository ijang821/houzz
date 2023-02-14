package houzz.service.mediationShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.domain.AuthInfoDTO;
import houzz.mapper.MediationShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MedDelete1Service {
    @Autowired
    MediationShipMapper mediationShipMapper;
	public void execute(HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		String medId = authInfoDTO.getUserId();
		mediationShipMapper.memDel(medId);
	}
     
}
