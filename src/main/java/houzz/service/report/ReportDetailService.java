package houzz.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.ReportCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.domain.ReportDTO;
import houzz.mapper.EmployeeMapper;
import houzz.mapper.ReportMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ReportDetailService {
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    EmployeeMapper employeeMapper;
	public void execute(String reportNum, Model model,HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		EmployeeDTO empDTO = employeeMapper.selectOne(authInfoDTO.getUserId());
		
		ReportDTO reportDTO = reportMapper.selectOne(reportNum);
		model.addAttribute("reportCommand",reportDTO);
		model.addAttribute("empNum",empDTO.getEmpNum());
	}
}
