package org.zerock.service;

import java.util.List;

import org.zerock.domain.CarVO;
import org.zerock.domain.Member;


public interface CarService {
	List<CarVO> selectCarList() throws Exception;
	
	public void carRegi(CarVO vo) throws Exception;
	
	public CarVO read(int Cid);

	public void delete(Member mem);

}
