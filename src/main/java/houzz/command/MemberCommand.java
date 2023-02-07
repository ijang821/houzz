package houzz.command;

import java.util.Date;

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
	Date memberBirth;
	String accountAddress;
}
