package houzz.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.domain.EstateDTO;
import houzz.domain.OptionsDTO;

@Repository(value = "houzz.mapper.EstateMapper")
public interface EstateMapper {
	public String estateNumGenerate();
	public Integer estateInsert(EstateDTO estDTO);
	public Integer estateOptions(OptionsDTO opDTO);
	public List<EstateDTO> selectAll(String estateWord);
	public EstateDTO selectOne(String estateNum);
	public Integer estateUpdate(EstateDTO estDTO);
	public Integer estateDelete(String estateNum);
	public OptionsDTO selectOptOne(String estateNum);
	public Integer updateOptions(OptionsDTO opDTO);
}
