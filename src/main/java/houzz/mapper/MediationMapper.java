package houzz.mapper;

import org.springframework.stereotype.Repository;

import houzz.domain.MediationDTO;

@Repository("houzz.mapper.MediationMapper")
public interface MediationMapper {
	public Integer mediationJoinService(MediationDTO dto);
	
	}
