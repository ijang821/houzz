package houzz.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.EmployeeDTO;
import houzz.mapper.EmployeeMapper;

@Service
public class EmployeeDetailService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String empNum, Model model) {
		EmployeeDTO empDTO = employeeMapper.selectOne(empNum);
		model.addAttribute("employeeCommand", empDTO);
	}
}
