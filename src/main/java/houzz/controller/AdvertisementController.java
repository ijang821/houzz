package houzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdvertisementController {
	@RequestMapping("advertise")
	public String advertise() {
		return "thymeleaf/advertise/adList";
	}
	
	@RequestMapping("INIstdpay_pc_req")
	public String adSignForm() {
		return "INIstdpay_pc_req";
	}
	
}
