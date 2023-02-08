package houzz.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MediationCommand {
	@NotBlank(message = "중개소 등록 번호를 입력해주세요")
	String mediationNum;
	@NotBlank(message = "사업자 번호를 입력해주세요")
	String businessRegiNum;
	@NotBlank(message = "아이디를 입력해주세요")
	@Size(min = 2 , max = 5)
	String mediationId;
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String mediationPw;
	@NotBlank(message = "비밀번호 확인을 입력해주세요")
	String mediationPwCon;
	@NotBlank(message = "대표자명을 입력해주세요")
	String ceoName;
	@NotBlank(message = "중개소 이름을 입력해주세요")
	String mediationName;
	@NotBlank(message = "중개소 소재지을 입력해주세요")
	String mediationAddr;
	@NotBlank(message = "전화번호를 입력해주세요")
	String mediationPhone;
	@Email(message="형식에 맞지 않습니다")
	@NotBlank(message = "이메일을 입력해주세요")
	String mediationEmail;
	//@NotBlank(message = "등록 가능 광고을 입력해주세요")
	Integer ableAdCount;
	
	public boolean isMediationPwEqualsMediationPwCon() {
		return mediationPw.equals(mediationPwCon);
	}
	
}
