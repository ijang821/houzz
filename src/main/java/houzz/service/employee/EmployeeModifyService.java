package houzz.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.EmployeeCommand;
import houzz.domain.EmployeeDTO;
import houzz.mapper.EmployeeMapper;

@Service
public class EmployeeModifyService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO empDTO = new EmployeeDTO();
		empDTO.setDepartmentNum(employeeCommand.getDepartmentNum());
		empDTO.setEmpAddr(employeeCommand.getEmpAddr());
		empDTO.setEmpBirth(employeeCommand.getEmpBirth());
		empDTO.setEmpEmail(employeeCommand.getEmpEmail());
		empDTO.setEmpId(employeeCommand.getEmpId());
		empDTO.setEmpName(employeeCommand.getEmpName());
		empDTO.setEmpNum(employeeCommand.getEmpNum());
		empDTO.setEmpPhone(employeeCommand.getEmpPhone());
		employeeMapper.empUpdate(empDTO);
	}
}
