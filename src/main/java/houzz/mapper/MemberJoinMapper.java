package houzz.mapper;

import org.springframework.stereotype.Repository;

import houzz.domain.MemberDTO;

@Repository("houzz.mapper.MemberJoinMapper")
public interface MemberJoinMapper {
	Integer memberJoinInsert(MemberDTO memDTO);

}
