package houzz.service.empShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.mapper.EmployeeMapper;
import houzz.mapper.EmployeeShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeInfoService {
    @Autowired
    EmployeeShipMapper employeeShipMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
	    String empId = authInfoDTO.getUserId();  
        EmployeeDTO dto = employeeShipMapper.selectEmp(empId);
        model.addAttribute("employeeCommand",dto);
	}
  
}
