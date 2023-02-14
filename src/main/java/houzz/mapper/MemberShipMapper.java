package houzz.mapper;


import org.springframework.stereotype.Repository;

import houzz.domain.MemberDTO;

@Repository("houzz.mapper.MemberShipMapper")
public interface MemberShipMapper {
    public MemberDTO selectMem(String memId);
	public Integer updatePw(MemberDTO dto);
	public MemberDTO selectOne(String memberNum);
	public void updateMem(MemberDTO dto);
	public void memDel(String memId);
	
	
	}

