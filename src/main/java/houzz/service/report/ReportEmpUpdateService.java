package houzz.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.ReportCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.domain.ReportDTO;
import houzz.mapper.EmployeeMapper;
import houzz.mapper.ReportMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ReportEmpUpdateService {
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    EmployeeMapper employeeMapper;
	public void execute(ReportCommand reportCommand, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		EmployeeDTO empDTO = employeeMapper.selectOne(authInfoDTO.getUserId());
		reportCommand.setEmpNum(empDTO.getEmpNum());
		ReportDTO dto = new ReportDTO();
		dto.setEmpNum(reportCommand.getEmpNum());
		dto.setReportProcess(reportCommand.getReportProcess());
		dto.setReportProcessDate(reportCommand.getReportProcessDate());
		reportMapper.reportUpdate(dto);
	}
}
