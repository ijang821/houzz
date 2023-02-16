package houzz.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.MemberCommand;
import houzz.domain.MemberDTO;
import houzz.mapper.MemberJoinMapper;
import houzz.service.EmailSendService;
import houzz.service.SMSSendService;

@Service
public class MemberJoinService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberJoinMapper memberJoinMapper;
	@Autowired
	SMSSendService smsSendService;
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	JavaMailSender javaMailSender;
	public void execute(MemberCommand memberCommand, Model model) {
		MemberDTO memDTO = new MemberDTO();
		memDTO.setAccountAddress(memberCommand.getAccountAddress());
		memDTO.setMemberAddr(memberCommand.getMemberAddr());
		memDTO.setMemberBirth(memberCommand.getMemberBirth());
		memDTO.setMemberEmail(memberCommand.getMemberEmail());
		memDTO.setMemberGender(memberCommand.getMemberGender());
		memDTO.setMemberId(memberCommand.getMemberId());
		memDTO.setMemberJoinDate(memberCommand.getMemberJoinDate());
		memDTO.setMemberName(memberCommand.getMemberName());
		memDTO.setMemberNum(memberCommand.getMemberNum());
		memDTO.setMemberPhone(memberCommand.getMemberPhone());
		memDTO.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));
		memberJoinMapper.memberJoinInsert(memDTO);
		model.addAttribute("memberName",memDTO.getMemberName());
		
		// 메일 보내기
		String content = "<html><body>"
				+ "안녕하세요. HOUZZ입니다. <BR />"
				+ memDTO.getMemberName() + "님 가입을 환영합니다.<br />"
				+ "<a href='http://localhost:8080/register/memberMail?receiver="
				+ memDTO.getMemberEmail()+"'> 가입을 완료하시려면 여기를 눌러주세요. </a>"
				+ "</body></html>";
		String subject = "가입환영인사";
		emailSendService.mailSend(content, subject, "administrator@gmail.com", memDTO.getMemberEmail());
		
		// SMS
		content = "안녕하세요. HOUZZ입니다.\n"
				+ memDTO.getMemberName()
				+ "님 가입을 환영합니다.\n"
				+ "이메일로 본인인증을 부탁드립니다.";
		smsSendService.send("01071461970", memDTO.getMemberPhone(), content);

	}
}