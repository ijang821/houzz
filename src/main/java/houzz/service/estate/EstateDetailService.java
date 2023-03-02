package houzz.service.estate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.EstateDTO;
import houzz.domain.MemberDTO;
import houzz.domain.OptionsDTO;
import houzz.domain.ReportDTO;
import houzz.mapper.EstateMapper;
import houzz.mapper.MemberMapper;
import houzz.mapper.ReportMapper;

@Service
public class EstateDetailService {
	@Autowired
	EstateMapper estateMapper;
	@Autowired
	ReportMapper reportMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(String estateNum, Model model ) {
		EstateDTO estDTO = estateMapper.selectOne(estateNum);
		OptionsDTO optDTO = estateMapper.selectOptOne(estateNum);
		MemberDTO memberDTO = memberMapper.selectOne(estDTO.getMemberNum());
		model.addAttribute("estateCommand", estDTO);
		model.addAttribute("estCommand", optDTO);
		model.addAttribute("memberDTO", memberDTO);
		System.out.println("이거다 !!!!"+estDTO);
		
		ReportDTO reportDTO = reportMapper.selectOne(estateNum);
		model.addAttribute("reProcess",reportDTO);
		System.out.println(reportDTO);
	}
}
