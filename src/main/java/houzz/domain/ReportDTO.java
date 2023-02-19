package houzz.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reportDTO")
public class ReportDTO {
     String reportNum;
     String estateNum;
     String memeberNum;
     String reportProcess;
     Date reportProcessDate;
     String reportTitle;
     String reportContent;
     String empNum;
     
}
