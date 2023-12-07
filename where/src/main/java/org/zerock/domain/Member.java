package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Member {


	private int mid;
	private String email;
	private String pw;
	private String niname;
	private String name;
	private Date moddate;
	private Date regdate;
	private char locagree;
	private String authority;
	private String sessionid;
	private String limittime;
	private String hashedPassword;
}
