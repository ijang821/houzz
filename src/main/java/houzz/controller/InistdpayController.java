package houzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import houzz.service.INIstdpayService;

@Controller
@RequestMapping("INIstdpay")
public class InistdpayController {
	@Autowired
	INIstdpayService iNIstdpayService;
	@RequestMapping("payDetail") 
	public String payDetail() {
		
		return "";
	}
	

}
