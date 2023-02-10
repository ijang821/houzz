package houzz.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentCommand {
	@NotBlank(message = "부서번호를 입력하여 주세요.")
	String departmentNum;
	@NotBlank(message = "부서명을 입력하여 주세요.")
	String departmentName;
}
