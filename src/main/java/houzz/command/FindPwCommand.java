package houzz.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindPwCommand {
	@NotBlank(message = "아이디를 입력해주세요.")
	String userId;
	@NotBlank(message = "연락처를 입력해주세요.")
	String userPhone;
	@NotBlank(message = "이메일을 입력해주세요.")
	String userEmail;
}
