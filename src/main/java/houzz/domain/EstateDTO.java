package houzz.domain;

import java.util.Date;

import lombok.Data;

@Data
public class EstateDTO {
	String estateNum;
	String estateName;
	String estateAddr;
	Long estatePrice;
	String estateOption;
	String estateInfo;
	String estatePic;
	String originalEstatePic;
	String estateExplain;
	Date estateRegiDate;
}
