package houzz.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("empDTO")
public class EmployeeDTO {
	String empNum;
	String departmentNum;
	String empId;
	String empPw;
	String empName;
	String empPhone;
	String empEmail;
	String empAddr;
	String empGender;
	Date empBirth;
}
