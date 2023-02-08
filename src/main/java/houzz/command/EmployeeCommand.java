package houzz.command;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeCommand {
	String empNum;
	String departmentNum;
	String empId;
	String empPw;
	String empPwCon;
	String empName;
	String empPhone;
	String empEmail;
	String empAddr;
	String empGender;
	Date empBirth;
}
