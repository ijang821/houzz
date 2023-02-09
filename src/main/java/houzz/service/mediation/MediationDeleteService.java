package houzz.service.mediation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.MediationMapper;

@Service
public class MediationDeleteService {
    @Autowired
    MediationMapper mediationMapper;
	public Integer execute(String mediationNum) {
		Integer i = mediationMapper.mediationDelete(mediationNum);
		return i;
	}

}
