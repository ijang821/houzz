package houzz.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.ReportCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.EstateDTO;
import houzz.domain.MemberDTO;
import houzz.mapper.EstateMapper;
import houzz.mapper.MemberShipMapper;
import houzz.mapper.ReportMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ReportAutoNumService {
	@Autowired
	ReportMapper reportMapper;
    @Autowired
    MemberShipMapper memberShipMapper;
	public void execute(ReportCommand reportCommand, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		MemberDTO memDTO = memberShipMapper.selectOne(authInfoDTO.getUserId());
		String num = reportMapper.selectReportAutoNum();
		
          reportCommand.setReportNum(num);
          reportCommand.setMemeberNum(memDTO.getMemberNum());
        
	}
}
