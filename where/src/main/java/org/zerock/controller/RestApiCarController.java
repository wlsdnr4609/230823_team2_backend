package org.zerock.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.CarVO;
import org.zerock.domain.Member;
import org.zerock.service.CarService;

/**
 * Handles requests for the application home page.
 */

@RestController
@RequestMapping("/api/car")
public class RestApiCarController {

	private static final Logger logger = LoggerFactory.getLogger(RestApiCarController.class);

	@Inject
	private CarService carService;

	// 차량리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<CarVO> carList(Model model) throws Exception {
		logger.info(". . . . . . . ./car/list");

		List<CarVO> list = carService.selectCarList();

		logger.info(". . . . . . . . .// list.toString()=" + list.toString());

		return list;
	}

	
	// cid로 차량정보 조회
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public CarVO read(@RequestBody CarVO vo) throws Exception {
		logger.info("read post ...........");
		logger.info(vo.toString());

		return carService.read(vo.getCid());

	}

	// 차량등록
	@RequestMapping(value = "/regi", method = RequestMethod.POST)
	public String carRegi(@RequestBody CarVO vo) throws Exception {

		logger.info("delete post ...........");
		logger.info(vo.toString());

		carService.carRegi(vo);

		return "succ";

		
	}
	
	// 차량정보삭제
		@RequestMapping(value = "/remove", method = RequestMethod.POST)
		public String delete(@RequestBody Member mem) throws Exception {

			logger.info("delete post ...........");
			logger.info(mem.toString());

			carService.delete(mem);

			return "succ";

		}
}
