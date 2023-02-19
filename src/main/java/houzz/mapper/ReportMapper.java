package houzz.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import houzz.command.ReportCommand;
import houzz.domain.ReportDTO;

@Repository("houzz.mapper.ReportMapper")
public interface ReportMapper {
	public String selectReportAutoNum();
	public List<ReportDTO> selectAll();
	public Integer reportInsert(ReportDTO dto);
	public ReportDTO selectOne(String reportNum);
	public Integer reportUpdate(ReportDTO dto);

}
