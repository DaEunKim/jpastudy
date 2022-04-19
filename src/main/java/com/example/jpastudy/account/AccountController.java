package com.example.jpastudy.account;

import com.example.jpastudy.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	private final AccountRepository accountRepository;
	private final JavaMailSender javaMailSender;

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

		// 회원 가입 처리
		Account account = Account.builder()
				.email(signUpForm.getEmail())
				.nickname(signUpForm.getNickname())
				.password(signUpForm.getPassword()) // TODO encoding 해야함
				.emailVerified(false)
				.studyCreatedByWeb(true) // 스터디 관련 web을 다 켜놓기
				.studyEnrollmentResultByWeb(true)
				.studyUpdatedResultByWeb(true)
				.build();

		Account newAccount = accountRepository.save(account);

		// email 보내기
		newAccount.generateEamilCheckToken();
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(newAccount.getEmail());
		mailMessage.setSubject("회원 가입 인증");
		mailMessage.setText("/check-email-token?token="+newAccount.getEmailCheckToken()+
				"&email="+ newAccount.getEmail());
		javaMailSender.send(mailMessage);


		return "redirect:/";
	}
}
