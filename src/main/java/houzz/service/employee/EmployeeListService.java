package houzz.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.EmployeeDTO;
import houzz.mapper.EmployeeMapper;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String empWord, Model model) {
		List<EmployeeDTO> list = employeeMapper.selectAll(empWord);
		model.addAttribute("list", list);
		model.addAttribute("empWord", empWord);
	}
}
