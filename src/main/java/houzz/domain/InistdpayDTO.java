package houzz.domain;

import java.security.Timestamp;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("inistdpayDTO")
public class InistdpayDTO {
	String resultCode;
    String resultMsg;
    String tid;
    String MOID;
    String TotPrice;
    String goodName;
    Date applDate;
    Timestamp applTime;
      
}
