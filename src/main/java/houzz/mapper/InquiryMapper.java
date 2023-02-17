package houzz.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.command.InquiryCommand;
import houzz.domain.InquiryDTO;


@Repository("houzz.mapper.InquiryMapper")
public interface InquiryMapper {
	public String inquiryNumGenerate();
	public Integer inquiryInsert(InquiryDTO dto);
	public String selectMem(String userId);
	public List<InquiryDTO> selectAll(String inquiryWord);
	public InquiryDTO selectOne(String inquiryNum);
	public Integer inquiryUpdate(InquiryDTO dto);
	public Integer inquiryDelete(String inquiryNum);
	public Integer mediationRemove(HashMap<String, Object> condition);
	public void inquiryAnswerUpdate(InquiryDTO dto);
	
	
	
}
