package org.zerock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.CarVO;
import org.zerock.domain.Member;
import org.zerock.mapper.CarMapper;

@Service
//@Configuration
//@MapperScan("org.zerock.mapper")
public class CarServiceImpl implements CarService {

	@Autowired
	private CarMapper carMapper;

	@Override
	public List<CarVO> selectCarList() throws Exception {
		return carMapper.selectCarList();
	}

	@Override
	@Transactional
	public CarVO read(int Cid) {
		return carMapper.read(Cid);
	}
	
	@Override
	@Transactional
	public void carRegi(CarVO vo) throws Exception {

		carMapper.carRegi(vo);
	}


//	@Override
//	@Transactional
//	public void modifyCar(Member mem) {
//		carMapper.modifyMember(mem);
//	}




	@Override
	@Transactional
	public void delete(Member mem) {
		carMapper.delete(mem);
	}

}
