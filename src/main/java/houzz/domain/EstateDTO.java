package houzz.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "estateDTO")
public class EstateDTO {
	String estateNum;
	String estateName;
	String estateAddr;
	Long estatePrice;
	String estateInfo;
	String estatePic;
	String originalEstatePic;
	String estateExplain;
	Date estateRegiDate;
	String memberNum;
}
