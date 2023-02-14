package houzz.service.empShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import houzz.domain.AuthInfoDTO;
import houzz.domain.EmployeeDTO;
import houzz.domain.MediationDTO;
import houzz.mapper.EmployeeShipMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmpPasswordService {
    @Autowired
    EmployeeShipMapper employeeShipMapper;
    @Autowired
	PasswordEncoder passwordEncoder;
	public void execute(String empPw, HttpSession session) {
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("authInfoDTO");
		String newPw = passwordEncoder.encode(empPw);
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpId(authInfoDTO.getUserId());
		dto.setEmpPw(newPw);
		employeeShipMapper.updatePwEmp(dto);
		authInfoDTO.setUserPw(newPw);
		
	}

}
