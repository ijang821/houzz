package houzz.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.mapper.EmployeeMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String empWord, Model model,HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		EmployeeDTO dto = employeeMapper.selectOne(authInfoDTO.getUserId());
		
		
		List<EmployeeDTO> list = employeeMapper.selectAll(empWord);
		model.addAttribute("list", list);
		model.addAttribute("empWord", empWord);
		model.addAttribute("dto",dto);
	}
}
