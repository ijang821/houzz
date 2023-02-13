package houzz.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("mediationDTO")
public class MediationDTO {
	String mediationNum;
	String businessRegiNum;
	String mediationId;
	String mediationPw;
	String ceoName;
	String mediationName;
	String mediationAddr;
	String mediationPhone;
	String mediationEmail;
	Integer ableAdCount;
}
