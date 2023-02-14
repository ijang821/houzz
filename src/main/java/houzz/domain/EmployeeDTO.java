package houzz.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
