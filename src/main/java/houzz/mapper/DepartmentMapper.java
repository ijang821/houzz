package houzz.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.domain.DepartmentDTO;

@Repository("houzz.mapper.DepartmentMapper")
public interface DepartmentMapper {
	public Integer departmentInsert(DepartmentDTO departmentDTO);
	public List<DepartmentDTO> selectAll();
}
