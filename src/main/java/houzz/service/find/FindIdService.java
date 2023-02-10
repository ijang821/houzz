package houzz.service.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import houzz.command.FindIdCommand;
import houzz.domain.AuthInfoDTO;
import houzz.mapper.FindMapper;

@Service
public class FindIdService {
	@Autowired
	FindMapper findMapper;

	public String execute(FindIdCommand findIdCommand, BindingResult result, Model model) {
		AuthInfoDTO authInfoDTO = findMapper.findId(findIdCommand.getMemberEmail());
		if (authInfoDTO == null) {
			result.rejectValue("memberEmail", "findIdCommand.memberEmail", "이메일이 틀렸습니다.");
			return "thymeleaf/find/findId";
		} else {
			if (!authInfoDTO.getPhone().equals(findIdCommand.getMemberPhone())) {
				result.rejectValue("memberPhone", "findIdCommand.memberPhone", "전화번호가 틀렸습니다.");
				return "thymeleaf/find/findId";
			}
			model.addAttribute("userId", authInfoDTO.getUserId());
			return "thymeleaf/find/findOk";
		}
	}
}
