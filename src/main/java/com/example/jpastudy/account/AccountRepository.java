package com.example.jpastudy.account;

import com.example.jpastudy.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : DaEunKim
 * @version : 2022/04/19
 * @fileName : com.example.jpastudy.account
 * @description :
 */
@Transactional(readOnly = true) // 성능에 이점을 위해
public interface AccountRepository extends JpaRepository<Account, Long> {
	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
}
