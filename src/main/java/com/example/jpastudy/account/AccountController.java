package com.example.jpastudy.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : DaEunKim
 * @version : 2022/03/28
 * @fileName : com.example.jpastudy.account
 * @description :
 */
@Controller
public class AccountController {

	@GetMapping("sign-up")
	public String signUpFrom(Model model){
		model.addAttribute("signUpForm", new SignUpForm());
		return "account/sign-up";
	}
}
