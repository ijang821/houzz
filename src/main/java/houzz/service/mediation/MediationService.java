package houzz.service.mediation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.MediationCommand;
import houzz.domain.MediationDTO;
import houzz.mapper.MediationMapper;

@Service
public class MediationService {
	@Autowired
	MediationMapper mediationMapper;
	public Integer execute(MediationCommand mediationCommand) {
		MediationDTO dto = new MediationDTO();
		dto.setMediationId(mediationCommand.getMediationId());
		dto.setMediatrionPw(mediationCommand.getMediatrionPw());
		dto.setBusinessRegiNum(mediationCommand.getBusinessRegiNum());
		dto.setCeoName(mediationCommand.getCeoName());
		dto.setMediationAddr(mediationCommand.getMediationAddr());
		dto.setMediationEmail(mediationCommand.getMediationEmail());
		dto.setMediationName(mediationCommand.getMediationName());
		dto.setMediationPhone(mediationCommand.getMediationPhone());
		dto.setAbleAdCount(mediationCommand.getAbleAdCount());
		dto.setMediationNum(mediationCommand.getMediationNum());
		Integer i = mediationMapper.mediationJoinService(dto);
		System.out.println(i +"개가 삽입되었습니다.");
		return i;
	}
}
