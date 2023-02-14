package houzz.service.estate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.EstateDTO;
import houzz.mapper.EstateMapper;

@Service
public class EstateListController {
	@Autowired
	EstateMapper estateMapper;
	public void execute(String estateWord, Model model) {
		List<EstateDTO> list = estateMapper.selectAll(estateWord);
		model.addAttribute("list", list);
		model.addAttribute("estateWord", estateWord);
	}
}
