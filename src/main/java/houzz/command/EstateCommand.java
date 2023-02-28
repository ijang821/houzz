package houzz.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstateCommand {
	@NotBlank(message = "매물번호를 입력하여 주세요.")
	String estateNum;
	@NotBlank(message = "매물이름을 입력하여 주세요.")
	String estateName;
	@NotBlank(message = "매물주소를 입력하여 주세요.")
	String estateAddr;
	@NotNull(message = "매물가격을 입력하여 주세요.")
	Long estatePrice;
	@NotBlank(message = "매물정보를 입력하여 주세요.")
	String estateInfo;
	String memberNum;
	
	MultipartFile [] estatePic;
	//String originalEstatePic;
	
	@NotBlank(message = "매물 상세 설명을 입력하여 주세요.")
	String estateExplain;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date estateRegiDate;
	
	boolean airConditioner;
	boolean washer;
	boolean bed;
	boolean desk;
	boolean closet;
	boolean tv;
	boolean shoeShelf;
	boolean refrigerator;
	boolean gasStove;
	boolean induction;
	boolean microwave;
	boolean doorLock;
	boolean bidet;
}
