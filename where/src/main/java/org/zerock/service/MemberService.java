package org.zerock.service;

import java.util.Date;
import java.util.List;

import org.zerock.domain.Member;
import org.zerock.dto.LoginDTO;

public interface MemberService {
	List<Member> selectMemberList() throws Exception;
	
	public void register(Member mem) throws Exception;
	
	public Member login(LoginDTO dto) throws Exception;
	
	public String getHashedPasswordByEmail(String email);

	public void keepLogin(String email, String id, Date sessionLimit);

	public Member emailCk(String email);

	public Member ninameCk(String niname);
	
	public Member readMember(String email);
	
//	public void modify(Member mem);
	
	public void modifyName(Member mem);

	public void modifyNiname(Member mem);

	public void modifyPw(Member mem);
	
	public void modifyLoca(Member mem);

	public Member read(int mid);

	public void delete(String email);

	Member midCk(String email);



//	public boolean authenticateUser(Member mem);

}
