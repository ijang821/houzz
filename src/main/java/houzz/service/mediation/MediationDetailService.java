package houzz.service.mediation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.MediationDTO;
import houzz.mapper.MediationMapper;

@Service
public class MediationDetailService {
	@Autowired
	MediationMapper mediationMapper;
	public void execute(String mediationNum, Model model) {
		MediationDTO medDTO = mediationMapper.selectOne(mediationNum);
		model.addAttribute("mediationCommand",medDTO);
	}
}
