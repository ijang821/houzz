package houzz.service.mediation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.MediationCommand;
import houzz.domain.MediationDTO;
import houzz.mapper.MediationMapper;
import houzz.service.EmailSendService;
import houzz.service.SMSSendService;

@Service
public class MediationService {
	@Autowired
	MediationMapper mediationMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	SMSSendService smsSendService;
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	JavaMailSender javaMailSender;

	public void execute(MediationCommand mediationCommand, Model model) {
		MediationDTO dto = new MediationDTO();
		dto.setMediationId(mediationCommand.getMediationId());
		dto.setMediationPw(passwordEncoder.encode(mediationCommand.getMediationPw()));
		dto.setBusinessRegiNum(mediationCommand.getBusinessRegiNum());
		dto.setCeoName(mediationCommand.getCeoName());
		dto.setMediationAddr(mediationCommand.getMediationAddr());
		dto.setMediationEmail(mediationCommand.getMediationEmail());
		dto.setMediationName(mediationCommand.getMediationName());
		dto.setMediationPhone(mediationCommand.getMediationPhone());
		dto.setAbleAdCount(mediationCommand.getAbleAdCount());
		dto.setMediationNum(mediationCommand.getMediationNum());
		dto.setMediationJoinOk(mediationCommand.getMediationJoinOk());
		model.addAttribute("mediationName", dto.getMediationName());
		mediationMapper.mediationJoinInsert(dto);

		// 메일 보내기
		String content = "<html><body>" 
		               + "안녕하세요. HOUZZ입니다. <BR />" 
		               + dto.getMediationName() + "님 가입을 환영합니다.<br />"
			           + "<a href='http://localhost:8080/register/mediationMail?receiver=" 
		               + dto.getMediationEmail()
			           + "'> 가입을 완료하시려면 여기를 눌러주세요. </a>" + "</body></html>";
		String subject = "가입환영인사";
		emailSendService.mailSend(content, subject, "administrator@gmail.com", dto.getMediationEmail());
        
		// SMS
				content = "안녕하세요. HOUZZ입니다.\n"
						+ dto.getMediationName()
						+ "님 가입을 환영합니다.\n"
						+ "이메일로 본인인증을 부탁드립니다.";
				smsSendService.send("01071461970", dto.getMediationPhone(), content);
	}
}
