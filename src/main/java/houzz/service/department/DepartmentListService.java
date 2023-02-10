package houzz.service.department;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.DepartmentDTO;
import houzz.mapper.DepartmentMapper;

@Component
@Service
public class DepartmentListService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(Model model) {
		List<DepartmentDTO> list = departmentMapper.selectAll();
		model.addAttribute("list", list);
		System.out.println("리스트 : "+ list);
	}
}
