package houzz.service.estate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.command.FieldCheckCommand;
import houzz.domain.FieldCheckDTO;
import houzz.mapper.EstateMapper;

@Service
public class FieldCkService {
	@Autowired
	EstateMapper estateMapper;
	Integer i;
	public void execute(String estateNum, FieldCheckCommand fieldCheckCommand) {
		FieldCheckDTO fieldCheckDTO = new FieldCheckDTO();
		fieldCheckDTO.setEstateNum(estateNum);
		fieldCheckDTO.setFieldCkDate(fieldCheckCommand.getFieldCkDate());
		fieldCheckDTO.setFieldCkOk(fieldCheckCommand.getFieldCkOk());
		estateMapper.fieldCk(fieldCheckDTO);
	}
}
