package houzz.service.estate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.EstateDTO;
import houzz.domain.OptionsDTO;
import houzz.domain.ReportDTO;
import houzz.mapper.EstateMapper;
import houzz.mapper.ReportMapper;

@Service
public class EstateDetailService {
	@Autowired
	EstateMapper estateMapper;
	@Autowired
	ReportMapper reportMapper;
	public void execute(String estateNum, Model model ) {
		EstateDTO estDTO = estateMapper.selectOne(estateNum);
		OptionsDTO optDTO = estateMapper.selectOptOne(estateNum);
		model.addAttribute("estateCommand", estDTO);
		model.addAttribute("estCommand", optDTO);
		
		ReportDTO reportDTO = reportMapper.selectOne(estateNum);
		model.addAttribute("reProcess",reportDTO);
		System.out.println(reportDTO);
	}
}
