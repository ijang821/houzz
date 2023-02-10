package houzz.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.domain.EmployeeDTO;

@Repository("houzz.mapper.EmployeeMapper")
public interface EmployeeMapper {
	public Integer employeeInsert(EmployeeDTO empDTO);
	public String employeeNumGenerate();
	public List<EmployeeDTO> selectAll(String empWord);
	public EmployeeDTO selectOne(String empNum);
	public Integer empUpdate(EmployeeDTO empDTO);
	public Integer empDelete(String empNum);
}
