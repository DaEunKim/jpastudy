package com.example.jpastudy.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.jpastudy.account.SignUpForm;
import com.example.jpastudy.account.AccountRepository;

/**
 * @author : DaEunKim
 * @version : 2022/04/19
 * @fileName : com.example.jpastudy.account
 * @description :
 */
@Component // Bean으로 주입받아야 되니까 같이 Bean이 되야함 spring Bean과 Bean들만 주입받을 수 있다. 그래서 명시적으로 넣어줘야함.
@RequiredArgsConstructor // 이걸 쓰면 private final 생성자를 만들어줌. 그냥 private 생성자는 안만들어줌. lombok이 제공. 그래서 autowire 없이도 의존성 주입 가능
public class SignUpFormValidator implements Validator {
	private final AccountRepository accountRepository;

	@Override
	public boolean supports(Class<?> aClass){
		return aClass.isAssignableFrom(SignUpForm.class);
	}
	@Override
	public void validate(Object object, Errors errors){
		SignUpForm signUpForm = (SignUpForm)object;
		if (accountRepository.existsByEmail(signUpForm.getEmail())) {
			errors.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일입니다.");
		}

		if (accountRepository.existsByNickname(signUpForm.getNickname())) {
			errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpForm.getEmail()}, "이미 사용중인 닉네임입니다.");
		}

	}
}
