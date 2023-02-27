package houzz.command;

import java.util.Date;

import lombok.Data;

@Data
public class FieldCheckCommand {
	String estateNum;
	Date fieldCkDate;
	String fieldCkOk;
}
