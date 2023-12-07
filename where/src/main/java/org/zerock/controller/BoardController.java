package org.zerock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.BoardService;

/**
 * Handles requests for the application home page.
 */

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;

	/*
	 * @RequestMapping(value = "/list2") public void
	 * boardList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws
	 * Exception { logger.info("// /board/list");
	 * 
	 * List<BoardVO> list = service.selectBoardList(); model.addAttribute("list",
	 * list);
	 * 
	 * model.addAttribute("list", service.listSearchCriteria(cri));
	 * 
	 * logger.info("// list.toString()=" + list.toString());
	 * 
	 * PageMaker pageMaker = new PageMaker(); pageMaker.setCri(cri);
	 * 
	 * // pageMaker.setTotalCount(service.listCountCriteria(cri));
	 * pageMaker.setTotalCount(service.listSearchCount(cri));
	 * model.addAttribute("pageMaker", pageMaker); }
	 */

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

		logger.info(cri.toString());

		// model.addAttribute("list", service.listCriteria(cri));
		model.addAttribute("list", service.listSearchCriteria(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		// pageMaker.setTotalCount(service.listCountCriteria(cri));
		pageMaker.setTotalCount(service.listSearchCount(cri));

		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bid") int bid, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {

		model.addAttribute(service.read(bid));
	}

	

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registGET() throws Exception {

		logger.info("regist get ...........");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr, HttpServletRequest request) throws Exception {

		logger.info("regist post ...........");
		logger.info(board.toString());
		String niname = request.getParameter("niname");
		String cont = request.getParameter("cont");
		board.setNiname(niname);
		board.setCont(cont);
		service.regist(board);

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	  public void modifyPagingGET(int bid, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

	    model.addAttribute(service.read(bid));
	  }

	  @RequestMapping(value = "/modify", method = RequestMethod.POST)
	  public String modifyPagingPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

	    logger.info(cri.toString());
	    service.modify(board);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    logger.info(rttr.toString());

	    return "redirect:/board/list";
	  }
	  
	  @RequestMapping("/getAttach/{bid}")
	  @ResponseBody
	  public List<String> getAttach(@PathVariable("bid")Integer bid)throws Exception{
	    
	    return service.getAttach(bid);
	  } 
}
