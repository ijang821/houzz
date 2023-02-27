package houzz.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.domain.MediationDTO;

@Repository("houzz.mapper.MediationMapper")
public interface MediationMapper {
	public Integer mediationJoinInsert(MediationDTO dto);
	public MediationDTO selectOne(String mediationNum);
	public List<MediationDTO> selectAll(String mediationWord);
	public Integer mediationUpdate(MediationDTO dto);
	public Integer mediationDelete(String mediationNum);
	public Integer mediationRemove(HashMap<String, Object> condition);
	public Integer mediJoinCk(String mediationEmail);
	
	
	
	
		
	}
	

