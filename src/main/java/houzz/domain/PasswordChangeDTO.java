package houzz.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("passwordChangeDTO")
public class PasswordChangeDTO {
    String tableName;
    String userIdColumn;
    String userPwColumn;
    String userId;
    String userPw;
}
