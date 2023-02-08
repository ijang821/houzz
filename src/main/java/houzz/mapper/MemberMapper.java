package houzz.mapper;

import org.springframework.stereotype.Repository;

import houzz.domain.MemberDTO;

@Repository(value = "houzz.mapper.MemberMapper")
public interface MemberMapper {
	public String memberNumGenerate();
	public Integer memberInsert(MemberDTO memDTO);
}
