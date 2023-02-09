package houzz.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("authInfoDTO")
public class AuthInfoDTO {
     String userId;
     String userPw;
     String userEmail;
     String grade;
     String memberJoinCk;
}
