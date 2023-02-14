package houzz.mapper;

import org.springframework.stereotype.Repository;

import houzz.domain.EmployeeDTO;

@Repository("houzz.mapper.EmployeeShipMapper")
public interface EmployeeShipMapper {
	public EmployeeDTO selectEmp(String empId);
	public void updateEmp(EmployeeDTO dto);
	public void updatePwEmp(EmployeeDTO dto);
	public void empDel(String empId);

}
