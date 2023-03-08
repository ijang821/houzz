package houzz.command;

import java.security.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class InistdpayCommand {
	 String resultCode;
     String resultMsg;
     String tid;
     String MOID;
     String TotPrice;
     String goodName;
     Date applDate;
     Timestamp applTime;
     
}
