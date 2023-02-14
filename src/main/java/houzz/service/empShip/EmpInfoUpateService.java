package houzz.service.empShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.EmployeeCommand;
import houzz.command.MediationCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.mapper.EmployeeShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmpInfoUpateService {
	@Autowired
	EmployeeShipMapper employeeShipMapper;

	public void execute(EmployeeCommand employeeCommand, Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		String empId = authInfoDTO.getUserId();

		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpBirth(employeeCommand.getEmpBirth());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setDepartmentNum(employeeCommand.getDepartmentNum());
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpGender(employeeCommand.getEmpGender());
		employeeShipMapper.updateEmp(dto);
		model.addAttribute("employeeCommand", dto);

	}

}
