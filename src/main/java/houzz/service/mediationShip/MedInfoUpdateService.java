package houzz.service.mediationShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.MediationCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.MediationDTO;
import houzz.mapper.MediationShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MedInfoUpdateService {
	@Autowired
	MediationShipMapper mediationShipMapper;
	public void execute(MediationCommand mediationCommand, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO"); 
		String medId = authInfoDTO.getUserId(); 
		
		MediationDTO dto = new MediationDTO();
		dto.setMediationName(mediationCommand.getMediationName());
		dto.setMediationPhone(mediationCommand.getMediationPhone());
		dto.setMediationEmail(mediationCommand.getMediationEmail());
		dto.setMediationAddr(mediationCommand.getMediationAddr());
		dto.setMediationId(mediationCommand.getMediationId());
		dto.setBusinessRegiNum(mediationCommand.getBusinessRegiNum());
		dto.setCeoName(mediationCommand.getCeoName());
		dto.setMediationNum(mediationCommand.getMediationNum());
		mediationShipMapper.updateMed(dto);
		model.addAttribute("mediationCommand",dto);
	}
}
