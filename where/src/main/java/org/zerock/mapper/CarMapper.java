package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.CarVO;
import org.zerock.domain.Member;

public interface CarMapper {
	List<CarVO> selectCarList() throws Exception;

	public CarVO read(int Cid);	
	
	public void carRegi(CarVO vo);

	public Member readMember(String email);
	
	public void modifyMember(Member mem);


	public void delete(Member mem);

}
