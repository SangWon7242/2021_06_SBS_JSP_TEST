package com.sbs.example.jspCommunity.dto;

import java.time.LocalDateTime;

import java.util.Map;

import lombok.Data;

@Data
public class Member {
	private int id;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String email;
	private String cellphoneNo;
	private int authLevel;

	private String extra__writer;
	private String extra__boardName;
	private String extra__boardCode;

	public Member(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (LocalDateTime) map.get("regDate");
		this.updateDate = (LocalDateTime) map.get("updateDate");
		this.loginId = (String) map.get("loginId");
		this.loginPw = (String) map.get("loginPw");
		this.name = (String) map.get("name");
		this.nickname = (String) map.get("nickname");
		this.email = (String) map.get("email");
		this.cellphoneNo = (String) map.get("cellphoneNo");
		this.authLevel = (int) map.get("authLevel");
	}

}
