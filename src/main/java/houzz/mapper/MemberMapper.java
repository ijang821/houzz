package houzz.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.command.MemberCommand;
import houzz.domain.AuthInfoDTO;
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
	public MemberDTO selectOne(MemberCommand memberCommand);
	public AuthInfoDTO memberEmail(String memberEmail);
	public String memberIdCk(String memberId);
	public Integer memJoinCk(String memberEmail);
	public Integer addAccountAddr(MemberDTO memDTO);
}
