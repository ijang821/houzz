package houzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.mapper.MediationMapper;

@Service
public class MediationEmailService {
    @Autowired
    MediationMapper mediationMapper;
    public int execute(String mediationEmail) {
    	int i = mediationMapper.mediJoinCk(mediationEmail);
    	return i;
    }
}
