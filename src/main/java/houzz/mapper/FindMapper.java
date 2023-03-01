package houzz.mapper;

import org.springframework.stereotype.Repository;

import houzz.command.FindPwCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.PasswordChangeDTO;

@Repository("houzz.mapper.FindMapper")
public interface FindMapper {

	public AuthInfoDTO findId(String memberEmail);
	public String findPw(FindPwCommand findPwCommand);
	public Integer changPw(PasswordChangeDTO dto);
   
}
