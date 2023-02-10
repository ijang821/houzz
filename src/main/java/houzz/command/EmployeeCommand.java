package houzz.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeCommand {
	String empNum;
	String departmentNum;
	@Size(min = 4, max = 12,message = "아이디는 4자에서 12자사이로 입력해 주세요!")
	String empId;
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,12}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상 12글자 이하")
	String empPw;
	@NotBlank(message = "비밀번호확인을 입력해 주세요.")
	String empPwCon;
	@NotBlank(message = "이름을 입력해 주세요.")
	String empName;
	@NotBlank(message = "연락처를 입력해 주세요.")
	String empPhone;
	@NotBlank(message = "이메일을 입력해 주세요.")
	String empEmail;
	@NotBlank(message = "주소를 입력해 주세요.")
	String empAddr;
	@NotBlank(message = "성별을 선택해 주세요")
	String empGender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull (message = "생년월일을 입력하여 주세요.")
	Date empBirth;
	
	public boolean isEmpPwEqualsEmpPwCon() {
		return empPw.equals(empPwCon);
	}
}
