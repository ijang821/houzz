package houzz.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("mediationDTO")
public class MediationDTO {
	String mediationNum;
	String businessRegiNum;
	String mediationId;
	String mediatrionPw;
	String ceoName;
	String mediationName;
	String mediationAddr;
	String mediationPhone;
	String mediationEmail;
	Integer ableAdCount;
}
