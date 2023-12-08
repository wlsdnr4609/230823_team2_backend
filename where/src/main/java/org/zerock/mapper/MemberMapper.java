package org.zerock.mapper;

import java.util.Date;
import java.util.List;

import org.zerock.domain.Member;
import org.zerock.dto.LoginDTO;

public interface MemberMapper {
	List<Member> selectMemberList() throws Exception;
	
	public void register(Member mem);
	
	public Member login(LoginDTO dto);

	public String getHashedPasswordByEmail(String email);

	public void keepLogin(String email, String id, Date sessionLimit);

	public Member readMember(String email);
	
	public void modifyMember(Member mem);
	
	public void modifyName(Member mem);
	
	public void modifyNiname(Member mem);

	public void modifyPw(Member mem);

    public void modifyLoca(Member mem);

	public Member read(int mid);

	public void delete(Member mem);

	public Member emailCk(String email);

	public Member ninameCk(String niname);




}
