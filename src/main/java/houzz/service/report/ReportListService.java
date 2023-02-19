package houzz.service.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.ReportDTO;
import houzz.mapper.ReportMapper;

@Service
public class ReportListService {
	@Autowired
	ReportMapper reportMapper;

	public void execute(Model model) {
		List<ReportDTO> list = reportMapper.selectAll();
		model.addAttribute("list", list);
	}
}