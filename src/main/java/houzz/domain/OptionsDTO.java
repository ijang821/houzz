package houzz.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "optionsDTO")
public class OptionsDTO {
	String estateNum;
	String airConditioner;
	String washer;
	String bed;
	String desk;
	String closet;
	String tv;
	String shoeShelf;
	String refrigerator;
	String gasStove;
	String induction;
	String microwave;
	String doorLock;
	String bidet;
}
