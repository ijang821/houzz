package houzz.service.mediationShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.AuthInfoDTO;
import houzz.domain.MediationDTO;
import houzz.mapper.MediationMapper;
import houzz.mapper.MediationShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MediationInfoService {
    @Autowired
    MediationShipMapper mediationShipMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		 String medId = authInfoDTO.getUserId();
		 System.out.println(medId);
		 MediationDTO dto =  mediationShipMapper.selectMed(medId);
		 model.addAttribute("mediationCommand",dto);
	}
      
}

