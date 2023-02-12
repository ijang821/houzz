package houzz.command;

import java.util.Date;

import lombok.Data;
@Data
public class EstateCommand {
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
