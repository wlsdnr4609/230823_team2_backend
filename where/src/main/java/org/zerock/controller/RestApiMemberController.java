package org.zerock.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import org.zerock.domain.Member;
import org.zerock.dto.LoginDTO;
import org.zerock.service.MemberService;

import util.JwtUtils;
import util.PasswordEncoder;

/**
 * Handles requests for the application home page.
 */

@RestController
@RequestMapping("/api/member")
public class RestApiMemberController {

	private static final Logger logger = LoggerFactory.getLogger(RestApiMemberController.class);

	private JwtUtils jwtUtils = new JwtUtils();

	private PasswordEncoder passwordEncoder;

	@Inject
	private MemberService memberService;

	// 회원리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Member> memberdList(Model model) throws Exception {
		logger.info("// /member/list");

		List<Member> list = memberService.selectMemberList();

		logger.info("// list.toString()=" + list.toString());

		return list;
	}

	// mid로 회원정보 조회
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public Member read(@RequestBody Member mem) throws Exception {
		logger.info("read post ...........");
		logger.info(mem.toString());

		return memberService.read(mem.getMid());

	}

	/// 회원가입
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(@RequestBody Member mem) throws Exception {

		logger.info("regist post ...........");
		logger.info(mem.toString());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(mem.getPw());

		System.out.println("해시된 비밀번호:" + hashedPassword);

//		boolean isPasswordMatch = passwordEncoder.matches(mem.getPw(), hashedPassword);
//		System.out.println("비밀번호 일치 여부:" + isPasswordMatch);

		mem.setPw(hashedPassword);

		memberService.register(mem);
		return "success";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public Member loginPOST(@RequestBody LoginDTO dto) throws Exception {

		logger.info("// /loginPost");
		logger.info(dto.toString());
		final String token = jwtUtils.generateToken(dto.getEmail());
		System.out.println("/*** encordingStr=" + token);

		String decordedStr = jwtUtils.getEmailFromToken(token);
		System.out.println("/*** decordingStr=" + decordedStr);

		boolean email = jwtUtils.validateToken(token, dto.getEmail());
		System.out.println("email=" + email);

		
		String storedHashedPassword = memberService.getHashedPasswordByEmail(dto.getEmail());

		logger.info("Stored Hashed Password: " + storedHashedPassword);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(dto.getPw(), storedHashedPassword);

		logger.info("비밀번호 일치 여부: " + isPasswordMatch);

		
		if (isPasswordMatch) {
			dto.setPw(storedHashedPassword);
			logger.info("/*** dto.toString()="+dto.toString());
			return memberService.login(dto);
		} else {
			// Passwords do not match, handle the error (e.g., return an error response)
			return null; // Adjust the return type accordingly
		}
	}
	
	
	@RequestMapping(value = "/loginCookie", method = RequestMethod.POST)
	public Member loginCookie(@RequestBody LoginDTO dto) throws Exception {

		logger.info("/*** /loginCookie 시작...");
		logger.info(dto.toString());
		final String token = jwtUtils.generateToken(dto.getEmail());
		System.out.println("/*** encordingStr=" + token);

		String decordedStr = jwtUtils.getEmailFromToken(token);
		System.out.println("/*** decordingStr=" + decordedStr);

		boolean email = jwtUtils.validateToken(token, dto.getEmail());
		System.out.println("email=" + email);

		
		String storedHashedPassword = memberService.getHashedPasswordByEmail(dto.getEmail());

		logger.info("Stored Hashed Password: " + storedHashedPassword);

		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//boolean isPasswordMatch = passwordEncoder.matches(dto.getPw(), storedHashedPassword);
		boolean isPasswordMatch = storedHashedPassword.equals(dto.getPw());

		logger.info("비밀번호 일치 여부: " + isPasswordMatch);

		
		if (isPasswordMatch) {
			//dto.setPw(storedHashedPassword);
			logger.info("/*** dto.toString()="+dto.toString());
			return memberService.login(dto);
		} else {
			// Passwords do not match, handle the error (e.g., return an error response)
			return null; // Adjust the return type accordingly
		}
	}

	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		Object obj = session.getAttribute("login");

		if (obj != null) {
			Member mem = (Member) obj;

			session.removeAttribute("login");
			session.invalidate();

			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				memberService.keepLogin(mem.getEmail(), session.getId(), new Date());
			}
		}

		return "success";

	}

	// 이메일 중복체크
	@RequestMapping(value = "/emailCk", method = RequestMethod.POST)
	public Member eamilCk(@RequestBody Member mem) throws Exception {
		logger.info("emailCk post ...........");
		logger.info(mem.toString());

		return memberService.emailCk(mem.getEmail());

	}

	// 닉네임 중복체크
	@RequestMapping(value = "/ninameCk", method = RequestMethod.POST)
	public Member ninameCk(@RequestBody Member mem) throws Exception {
		logger.info("ninameCk post ...........");
		logger.info(mem.toString());

		return memberService.ninameCk(mem.getNiname());

	}

	// 마이페이지 회원정보 조회
	@RequestMapping(value = "/readMember", method = RequestMethod.POST)
	public Member readMember(@RequestBody Member mem) throws Exception {

		// model.addAttribute("mem", memberService.readMember(email));
		logger.info("조회할 이메일 : " + mem.getEmail());

		return memberService.readMember(mem.getEmail());
	}

	// 마이페이지 회원정보 수정
	@RequestMapping(value = "/modifyMember", method = RequestMethod.POST)
	public String modifyMemberPOST(@RequestBody Member mem, RedirectAttributes rttr) throws Exception {

		logger.info(mem.toString());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(mem.getPw());

		System.out.println("해시된 비밀번호:" + hashedPassword);

//		boolean isPasswordMatch = passwordEncoder.matches(mem.getPw(), hashedPassword);
//		System.out.println("비밀번호 일치 여부:" + isPasswordMatch);

		mem.setPw(hashedPassword);
		
		memberService.modifyMember(mem);

		rttr.addAttribute("name", mem.getName());
		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info(rttr.toString());

		return "SUCCESS";

	}
	@RequestMapping(value = "/modifyName", method = RequestMethod.POST)
	public String modifyNamePOST(@RequestBody Member mem, RedirectAttributes rttr) throws Exception {

		logger.info(mem.toString());

		memberService.modifyName(mem);

		rttr.addAttribute("name", mem.getName());
		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info(rttr.toString());

		return "SUCCESS";

	}

	@RequestMapping(value = "/modifyNiname", method = RequestMethod.POST)
	public String modifyNinamePOST(@RequestBody Member mem, RedirectAttributes rttr) throws Exception {

		logger.info(mem.toString());

		memberService.modifyNiname(mem);

		rttr.addAttribute("niname", mem.getNiname());
		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info(rttr.toString());

		return "SUCCESS";
	}

	@RequestMapping(value = "/modifyPw", method = RequestMethod.POST)
	public String modifyPwPOST(@RequestBody Member mem, RedirectAttributes rttr) throws Exception {

		logger.info(mem.toString());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(mem.getPw());

		System.out.println("해시된 비밀번호:" + hashedPassword);

//		boolean isPasswordMatch = passwordEncoder.matches(mem.getPw(), hashedPassword);
//		System.out.println("비밀번호 일치 여부:" + isPasswordMatch);

		mem.setPw(hashedPassword);
		memberService.modifyPw(mem);

		rttr.addAttribute("pw", mem.getPw());
		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info(rttr.toString());

		return "SUCCESS";
	}

	@RequestMapping(value = "/modifyLocagree", method = RequestMethod.POST)
	public String modifyLocagreePOST(@RequestBody Member mem, RedirectAttributes rttr) throws Exception {

		logger.info(mem.toString());

		memberService.modifyLoca(mem);

		rttr.addAttribute("locagree", mem.getLocagree());
		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info(rttr.toString());

		return "SUCCESS";
	}

	// 회원탈퇴
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String delete(@RequestBody Member mem) throws Exception {

		logger.info("delete post ...........");
		logger.info(mem.toString());

		memberService.delete(mem);

		return "succ";

	}
}
