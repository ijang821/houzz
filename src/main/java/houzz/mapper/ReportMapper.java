package houzz.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.domain.ReportDTO;

@Repository("houzz.mapper.ReportMapper")
public interface ReportMapper {
	public String selectReportAutoNum();
	public List<ReportDTO> selectAll(String reportNum);
	public Integer reportInsert(ReportDTO dto);
	public ReportDTO selectOne(String reportNum);
	public Integer reportUpdate(ReportDTO dto);
	public Integer reportRemove(HashMap<String, Object> condition);

}
