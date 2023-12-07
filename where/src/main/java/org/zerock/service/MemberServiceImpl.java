package org.zerock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Member;
import org.zerock.dto.LoginDTO;
import org.zerock.mapper.MemberMapper;

@Service
//@Configuration
//@MapperScan("org.zerock.mapper")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public List<Member> selectMemberList() throws Exception {
		return memberMapper.selectMemberList();
	}

	@Override
	@Transactional
	public void register(Member mem) throws Exception {

		memberMapper.register(mem);
	}

	/*
	 * @Override public boolean authenticateUser(Member mem) { // Retrieve hashed
	 * password from the database based on the username // Your data access logic
	 * here memberMapper.readMember(mem.getEmail());
	 * 
	 * if (mem.getEmail() == null) { return false; // User not found } String
	 * hashedPasswordFromDatabase = mem.getPw();
	 * 
	 * // Verify the entered password return
	 * PasswordEncoder.verifyPassword(mem.getPw(), hashedPasswordFromDatabase); }
	 */

	@Override
	@Transactional
	public Member login(LoginDTO dto) throws Exception {

		return memberMapper.login(dto);
	}

	@Override
	@Transactional
	public String getHashedPasswordByEmail(String email) {
		return memberMapper.getHashedPasswordByEmail(email);
	}

	@Override
	@Transactional
	public void keepLogin(String email, String id, Date sessionLimit) {
		memberMapper.keepLogin(email, id, sessionLimit);
	}

	@Override
	@Transactional
	public Member emailCk(String email) {
		return memberMapper.emailCk(email);
	}

	@Override
	public Member ninameCk(String niname) {
		return memberMapper.ninameCk(niname);
	}

	@Override
	@Transactional
	public Member readMember(String email) {
		return memberMapper.readMember(email);
	}

//	@Override
//	@Transactional
//	public void modify(Member mem) {
//		memberMapper.modify(mem);
//	}

	@Override
	@Transactional
	public void modifyName(Member mem) {
		memberMapper.modifyNiname(mem);
	}

	@Override
	@Transactional
	public void modifyNiname(Member mem) {
		memberMapper.modifyNiname(mem);
	}

	@Override
	@Transactional
	public void modifyPw(Member mem) {
		memberMapper.modifyPw(mem);
	}

	@Override
	@Transactional
	public void modifyLoca(Member mem) {
		memberMapper.modifyLoca(mem);
	}

	@Override
	@Transactional
	public Member read(int mid) {
		return memberMapper.read(mid);
	}

	@Override
	@Transactional
	public void delete(String email) {
		memberMapper.delete(email);
	}

	@Override
	public Member midCk(String email) {
		// TODO Auto-generated method stub
	  return memberMapper.midCk(email);
	}

	

}
