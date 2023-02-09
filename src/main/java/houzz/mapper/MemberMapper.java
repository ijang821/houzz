package houzz.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.domain.MemberDTO;

@Repository(value = "houzz.mapper.MemberMapper")
public interface MemberMapper {
	public String memberNumGenerate();
	public Integer memberInsert(MemberDTO memDTO);
	public List<MemberDTO> selectAll(String memberWord);
	public MemberDTO selectOne(String memberNum);
	public Integer memberUpdate(MemberDTO memDTO);
	public Integer memberDelete(String memberNum);
	public Integer membersRemove(HashMap<String, Object> condition);

}
