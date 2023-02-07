package houzz.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberDTO {
	String memberNum;
	String memberName;
	String memberId;
	String memberPw;
	String memberPhone;
	String memberEmail;
	String memberAddr;
	String memberGender;
	Date memberBirth;
	String accountAddress;
	Date memberJoinDate;
	String memberJoinCk;
}
