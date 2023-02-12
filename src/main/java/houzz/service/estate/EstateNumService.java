package houzz.service.estate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.EstateCommand;
import houzz.mapper.EstateMapper;

@Service
public class EstateNumService {
	@Autowired
	EstateMapper estateMapper;
	public void execute(EstateCommand estateCommand) {
		String num = estateMapper.estateNumGenerate();
		estateCommand.setEstateNum(num);
	}
}
