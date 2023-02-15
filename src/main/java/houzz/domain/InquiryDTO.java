package houzz.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("inquiryDTO")
public class InquiryDTO {
    String inquiryNum;
    String memberNum;
    Date inquiryDate;
	String inquiryTitle;
	String inquiryContent;
	String empNum;
	String answerContent;
	Date answerDate;


}
