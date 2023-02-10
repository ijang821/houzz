package houzz.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.EmployeeMapper;

@Service
public class EmployeeDelService {
	@Autowired
	EmployeeMapper employeeMapper;
	public Integer execute(String empNum) {
		Integer i = employeeMapper.empDelete(empNum);
		return i;
	}
}
