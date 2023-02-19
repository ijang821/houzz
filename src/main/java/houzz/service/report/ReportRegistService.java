package houzz.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.ReportCommand;
import houzz.domain.ReportDTO;
import houzz.mapper.ReportMapper;

@Service
public class ReportRegistService {
	@Autowired
	ReportMapper reportMapper;
     Integer i;
	public void execute(ReportCommand reportCommand) {
		
		ReportDTO dto = new ReportDTO();
		dto.setEstateNum(reportCommand.getEstateNum());
		dto.setMemeberNum(reportCommand.getMemeberNum());
		dto.setReportNum(reportCommand.getReportNum());
		dto.setReportTitle(reportCommand.getReportTitle());
		dto.setReportContent(reportCommand.getReportContent());
		i = reportMapper.reportInsert(dto);
		System.out.println(i +"개가 삽입되었습니다.");
	}
}
