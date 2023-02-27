package houzz.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "fieldCheckDTO")
public class FieldCheckDTO {
	String estateNum;
	Date fieldCkDate;
	String fieldCkOk;
}
