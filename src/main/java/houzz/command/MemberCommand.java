package houzz.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	String memberNum;
	String memberName;
	String memberId;
	String memberPw;
	String memberPwCon;
	String memberPhone;
	String memberEmail;
	String memberAddr;
	String memberGender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberBirth;
	String accountAddress;
	
}
