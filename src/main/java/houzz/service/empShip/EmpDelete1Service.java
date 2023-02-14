package houzz.service.empShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.domain.AuthInfoDTO;
import houzz.mapper.EmployeeShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmpDelete1Service {
    @Autowired
    EmployeeShipMapper employeeShipMapper;
	public void execute(HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		String empId = authInfoDTO.getUserId();
		employeeShipMapper.empDel(empId);
	
	}
    
}
