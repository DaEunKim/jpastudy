package com.example.jpastudy.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author : DaEunKim
 * @version : 2022/03/28
 * @fileName : com.example.jpastudy.account
 * @description :
 */
@Controller
public class AccountController {

	@GetMapping("/sign-up")
	public String signUpFrom(Model model){
		model.addAttribute(new SignUpForm());
		return "account/sign-up";
	}

	@PostMapping("/sign-up")
	public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors){
		if(errors.hasErrors()){
			return "account/sign-up";
		}
		return "redirect:/";
	}
}
