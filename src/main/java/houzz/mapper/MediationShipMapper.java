package houzz.mapper;

import org.springframework.stereotype.Repository;

import houzz.domain.MediationDTO;

@Repository("houzz.mapper.MediationShipMapper")
public interface MediationShipMapper {
	public MediationDTO selectMed(String medId);
	public void updatePwMed(MediationDTO dto);
	public void updateMed(MediationDTO dto);
	public void memDel(String medId);
}
