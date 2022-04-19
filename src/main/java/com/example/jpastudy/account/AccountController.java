package com.example.jpastudy.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author : DaEunKim
 * @version : 2022/03/28
 * @fileName : com.example.jpastudy.account
 * @description :
 */
@Controller
@RequiredArgsConstructor
public class AccountController {

	private final SignUpFormValidator signUpFormValidator;

	/**
	 * @author : DaEunKim
	 * @Description 이런 코드를 직접 작성하지 않고 바인더를 설정하여 사용
	 *      signUpFormValidator.validate(signUpForm, errors);
	 * 		if(errors.hasErrors()){
	 * 			return "account/sign-up";
	 *      }
	 */
	@InitBinder("signUpForm") // signUpForm 데이터를 받을때 바인더를 사용, 설명.
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(signUpFormValidator); // signUpForm 검증이 됨.
	}

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

		// 가입 처리

		return "redirect:/";
	}
}
