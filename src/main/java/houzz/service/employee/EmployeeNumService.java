package houzz.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.EmployeeCommand;
import houzz.mapper.EmployeeMapper;

@Service
public class EmployeeNumService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmployeeCommand employeeCommand) {
		String num = employeeMapper.employeeNumGenerate();
		employeeCommand.setEmpNum(num);
	}
}
