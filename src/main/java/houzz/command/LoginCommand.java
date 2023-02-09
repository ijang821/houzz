package houzz.command;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.val;

@Data
@Alias(value = "login1")
public class LoginCommand {
	@NotBlank(message = "아이디를 입력해주세요")
	String userId;
	@NotBlank(message = "비밀번호를 입력해주세요")
	String userPw;

}
