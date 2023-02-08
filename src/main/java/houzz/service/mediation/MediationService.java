package houzz.service.mediation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import houzz.command.MediationCommand;
import houzz.domain.MediationDTO;
import houzz.mapper.MediationMapper;

@Service
public class MediationService {
	@Autowired
	MediationMapper mediationMapper;

	public Integer execute(MediationCommand mediationCommand, Model model) {
		MediationDTO dto = new MediationDTO();
		dto.setMediationId(mediationCommand.getMediationId());
		dto.setMediationPw(mediationCommand.getMediationPw());
		dto.setBusinessRegiNum(mediationCommand.getBusinessRegiNum());
		dto.setCeoName(mediationCommand.getCeoName());
		dto.setMediationAddr(mediationCommand.getMediationAddr());
		dto.setMediationEmail(mediationCommand.getMediationEmail());
		dto.setMediationName(mediationCommand.getMediationName());
		dto.setMediationPhone(mediationCommand.getMediationPhone());
		dto.setAbleAdCount(mediationCommand.getAbleAdCount());
		dto.setMediationNum(mediationCommand.getMediationNum());
		model.addAttribute("MediationName",dto.getMediationName());
		Integer i = mediationMapper.mediationJoinInsert(dto);
		System.out.println(i +"개가 삽입되었습니다.");
		return i;
	}
}
