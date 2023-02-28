package houzz.service.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.EmployeeMapper;

@Service
public class EmployeeDelsService {
	@Autowired
    EmployeeMapper employeeMapper;
	public void execute(String empDels[]) {
		List<String> cs3 = new ArrayList<String>();
		for(String num : empDels ) {
			cs3.add(num);
		}
		HashMap<String, Object> condition =
				new HashMap<String, Object>();
		condition.put("employeeNums", cs3);
		employeeMapper.employeeRemove(condition);
	}
}
