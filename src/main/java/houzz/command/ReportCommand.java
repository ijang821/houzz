package houzz.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class ReportCommand {
	 String reportNum;
     String estateNum;
     String memeberNum;
     String reportProcess;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     Date reportProcessDate;
     String reportTitle;
     String reportContent;
     String empNum;
     
}
