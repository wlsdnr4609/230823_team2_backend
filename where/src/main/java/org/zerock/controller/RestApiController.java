package org.zerock.controller;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.BoardService;

/**
 * Handles requests for the application home page.
 */

@RestController
@RequestMapping("/api")
public class RestApiController {

   private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

   @Inject
   private BoardService service;

   @RequestMapping(value = "/list", method = RequestMethod.GET)
   public List<BoardVO> boardList(
           @RequestParam(value = "btype", required = false) Character btype,
           @ModelAttribute(value = "cri") SearchCriteria criteria,
           Model model,
           HttpServletResponse response) throws Exception {
       logger.info("// /board/list");

       List<BoardVO> list;

       if (btype != null) {
           // Handle the case for boardType parameter
           list = service.selectBoardList(btype);
       } else if (criteria != null) {
           // Handle the case for SearchCriteria parameter
           list = service.listSearchCriteria(criteria);

           PageMaker pageMaker = new PageMaker();
           pageMaker.setCri(criteria);
           pageMaker.setTotalCount(service.listSearchCount(criteria));

           model.addAttribute("pageMaker", pageMaker);

           // Set x-total-count header
           response.setHeader("x-total-count", String.valueOf(pageMaker.getTotalCount()));
           return list;
       } else {
           // Handle the case where neither boardType nor criteria is provided
           // You may throw an exception or handle it according to your requirement
           return Collections.emptyList();
       }

       // Set x-total-count header
       response.setHeader("x-total-count", String.valueOf(list.size()));
       return list;
   }
      
   @RequestMapping(value = "/qlist", method = RequestMethod.POST) public List<BoardVO>
   qlist(@RequestBody BoardVO board,@RequestParam(name = "btype", required = false) Character btype) throws Exception {
   
      String niname = board.getNiname();
   
      return service.qList(btype,niname);
   }
   
   @RequestMapping(value = "/write", method = RequestMethod.GET)
   public ResponseEntity<String> registerGET() {
      try {
         // Your logic for handling GET request for registration
         logger.info("register get ...........");
         return ResponseEntity.ok("Registration GET request successful.");
      } catch (Exception e) {
         // Handle the exception and return an appropriate HTTP status code
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body("Error processing registration GET request.");
      }
   }

   @RequestMapping(value = "/write", method = RequestMethod.POST)
   public String registPOST(@RequestBody BoardVO board) throws Exception {

      logger.info("regist post ...........");
      logger.info(board.toString());

      service.regist(board);
      // rttr.addFlashAttribute("msg", "SUCCESS");

      return "succ";
   }

   @RequestMapping(value = "/read", method = RequestMethod.GET)
   public ResponseEntity<BoardVO> readPage(@RequestParam("bid") int bid) {
      try {
         BoardVO result = service.read(bid);
         return ResponseEntity.ok().body(result);
      } catch (Exception e) {
         // 에러 처리 로직 추가
         return null;
      }

   }

   @RequestMapping(value = "/read", method = RequestMethod.POST)
   public BoardVO read(@RequestBody BoardVO board) throws Exception {
      logger.info("read post ...........");
      logger.info(board.toString());

      return service.read(board.getBid());

   }

   @RequestMapping(value = "/remove", method = RequestMethod.POST)
   public String remove(@RequestBody BoardVO board) throws Exception {

      logger.info("register post ...........");
      logger.info(board.toString());

      service.delete(board);

      return "succ";

   }

   @RequestMapping(value = "/modify", method = RequestMethod.GET)
   public ResponseEntity<BoardVO> modifyPagingGET(int bid, @ModelAttribute("cri") SearchCriteria cri, Model model)
         throws Exception {
      try {
         BoardVO result = service.read(bid);
         return ResponseEntity.ok().body(result);
      } catch (Exception e) {
         // 에러 처리 로직 추가
         return null;
         /* return ResponseEntity.status(500).build(); */
      }

   }

   @RequestMapping(value = "/modify", method = RequestMethod.POST)
   public String modifyPagingPOST(@RequestBody BoardVO board) throws Exception {

      logger.info("modifyPagingpost...........");
      service.modify(board);

      logger.info(board.toString());

      return "succ";
   }
   @RequestMapping(value = "/likes", method = RequestMethod.GET)
   public ResponseEntity<BoardVO> likesGET(int bid)
         throws Exception {
      try {
         BoardVO result = service.read(bid);
         return ResponseEntity.ok().body(result);
      } catch (Exception e) {
         // 에러 처리 로직 추가
         return null;
         /* return ResponseEntity.status(500).build(); */
      }

   }

   @RequestMapping(value = "/likes", method = RequestMethod.POST)
   public String likesPOST(@RequestBody BoardVO board) throws Exception {

      service.likes(board);

      logger.info(board.toString());
      
      return "succ";
   }
   

   @RequestMapping("/getAttach/{bid}")
   @ResponseBody
   public List<String> getAttach(@PathVariable("bid") Integer bid) throws Exception {

      return service.getAttach(bid);
   }
}

 