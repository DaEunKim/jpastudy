package com.example.jpastudy.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author : DaEunKim
 * @version : 2022/03/28
 * @fileName : com.example.jpastudy.domain
 * @description :
 */
@Entity
@Getter @Setter
/* rest api 개발을 위해, 연관관계가 복잡해질때
* EqualsAndHashCode에서 서로 다른 연관관계를 순환 참조하느라
* 무한루프가 발생하고 스택오버플로우가 발생할 수 있기 때문에 id만 사용 */
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue
	private Long id;

	/* 로그인에 필요한 값 */
	@Column(unique = true) // 유일한 값이여야 하기 때문
	private String email;

	@Column(unique = true) // 유일한 값이여야 하기 때문
	private String nickname;

	private String password;

	private boolean emailVerified; // 이메일 인증 절차

	private String emailCheckToken; // 이메일 검증 토큰

	private LocalDateTime joinedAt; // 가입일

	private String bio; // 자기소개

	private String url; // 홍페이지 주소

 	private String occupation; // 직업

	private String location; // 거주지

	// String 은 varchar(255)가 기본으로 매핑됨
	@Lob // text 타입에 매핑해줌 (varchar 보다 크기가 커질 수 있기에)
	@Basic(fetch = FetchType.EAGER) // 기본은 lazy
	private String profileImage; // 프로필 이미지

	//	알림 설정
	private boolean studyCreatedByEmail; // 스터디가 만들어졌다는 것을 이메일로 받을 것인지

	private boolean studyCreatedByWeb; // 웹으로 받을 것인지

	private boolean studyEnrollmentResultByEmail; // 스터디 가입 신청 결과를 이메일로 받을 것인지

	private boolean studyEnrollmentResultByWeb; // 웹으로 받을 것인지

	private boolean studyUpdatedResultByEmail; // 스터디 갱신된 정보를 이메일로 받을것인지

	private boolean studyUpdatedResultByWeb; // 웹으로 받을 것인지
}
