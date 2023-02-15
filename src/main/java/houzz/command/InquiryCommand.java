package houzz.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InquiryCommand {
	String inquiryNum;
    String memberNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date inquiryDate;
	String inquiryTitle;
	String inquiryContent;
	String empNum;
	String answerContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date answerDate;
}
