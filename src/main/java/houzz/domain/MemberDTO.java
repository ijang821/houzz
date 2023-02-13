package houzz.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias(value = "memberDTO")

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
