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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import org.zerock.domain.Member;
import org.zerock.dto.LoginDTO;
import org.zerock.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	private MemberService memberService;

	@RequestMapping(value = "/list")
	public void memberList(Model model) throws Exception {
		logger.info("// /member/list");

		List<Member> list = memberService.selectMemberList();

		logger.info("// list.toString()=" + list.toString());

		model.addAttribute("list", list);
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registGET() throws Exception {
		logger.info("regist get ...........");
	}


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(Member mem) throws Exception {

		logger.info("regist post ...........");
		logger.info(mem.toString());

		memberService.register(mem);

		return "redirect:/member/login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
		// login.jsp로 이동
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public String loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {

		Member mem = memberService.login(dto);

		if (mem == null) {
			model.addAttribute("에러", "공백 or 사용자 이름과 비밀번호를 확인하세요.");
			return "redirect:/member/login";
		}

		model.addAttribute("mem", mem);

		if (dto.isUseCookie()) {

			int amount = 60 * 60 * 24 * 7;
			// 일주일의 시간을 amount에 담아
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
			// 현재 시간에 일주일치 ms초를 더함
			memberService.keepLogin(mem.getEmail(), session.getId(), sessionLimit);
		}
		return null;
	}

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

		return "redirect:/member/logout";

	}

	@RequestMapping(value = "/modifyMember", method = RequestMethod.GET)
	public String modifyMemberGET(@ModelAttribute("email") String email, Model model, HttpSession session)
			throws Exception {

		model.addAttribute("mem", memberService.readMember(email));
		logger.info("조회할 이메일 : " + email);

		return "redirect:/board/mypage";
	}

//	@RequestMapping(value = "/modifyMember", method = RequestMethod.POST)
//	public String modifyMemberPOST(Member mem, RedirectAttributes rttr) throws Exception {
//
//		logger.info(mem.toString());
//
//		memberService.modify(mem);
//
//		rttr.addAttribute("niname", mem.getNiname());
//		rttr.addAttribute("pw", mem.getPw());
//		rttr.addAttribute("locagree", mem.getLocagree());
//
//		rttr.addFlashAttribute("msg", "SUCCESS");
//
//		logger.info(rttr.toString());
//
//		return "redirect:/board/mypage";
//	}

}
