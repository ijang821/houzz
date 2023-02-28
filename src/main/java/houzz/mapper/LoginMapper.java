package houzz.mapper;

import org.springframework.stereotype.Repository;

import houzz.domain.AuthInfoDTO;


@Repository("houzz.mapper.LoginMapper")
public interface LoginMapper {
	public AuthInfoDTO loginselect(String userId);
	public AuthInfoDTO addressLogin(String address);
}

