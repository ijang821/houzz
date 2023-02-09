package houzz.service.mediation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.MediationDTO;
import houzz.mapper.MediationMapper;

@Service
public class MediationListService {
	@Autowired
	MediationMapper mediationMapper;
	public void execute(Model model, String mediationWord) {
        List<MediationDTO> list = mediationMapper.selectAll(mediationWord);
        model.addAttribute("list",list);
        model.addAttribute("mediationWord",mediationWord);
	}
}
