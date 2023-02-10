package houzz.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "departmentDTO")
public class DepartmentDTO {
	String departmentNum;
	String departmentName;
}
