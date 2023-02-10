package houzz.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.DepartmentCommand;
import houzz.domain.DepartmentDTO;
import houzz.mapper.DepartmentMapper;

@Service
public class DepartmentRegistService {
	@Autowired
	DepartmentMapper departmentMapper;
	public Integer execute(DepartmentCommand departmentCommand) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setDepartmentNum(departmentCommand.getDepartmentNum());
		departmentDTO.setDepartmentName(departmentCommand.getDepartmentName());
		Integer i = departmentMapper.departmentInsert(departmentDTO);
		return i;
	}
}
