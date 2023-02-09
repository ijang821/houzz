package houzz.service.mediation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import houzz.command.MediationCommand;
import houzz.domain.MediationDTO;
import houzz.mapper.MediationMapper;

@Service
public class MediationModifyService {
	
	@Autowired
	MediationMapper mediationMapper;
	Integer i;
	public Integer execute(MediationCommand mediationCommand) {
		MediationDTO dto = new MediationDTO();
		dto.setBusinessRegiNum(mediationCommand.getBusinessRegiNum());
		dto.setCeoName(mediationCommand.getCeoName());
		dto.setMediationAddr(mediationCommand.getMediationAddr());
		dto.setMediationEmail(mediationCommand.getMediationEmail());
		dto.setMediationName(mediationCommand.getMediationName());
		dto.setMediationPhone(mediationCommand.getMediationPhone());
		dto.setMediationNum(mediationCommand.getMediationNum());
		Integer i = mediationMapper.mediationUpdate(dto);
		System.out.println(i +"개가 업데이트 되었습니다.");
		return i;
	}
}
